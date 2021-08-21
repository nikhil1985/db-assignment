import React, { useState, useEffect, useRef } from "react";
import UploadService from "../services/FileUploadService";

const UploadFiles = () => {
  const [selectedFiles, setSelectedFiles] = useState(undefined);
  const [progressInfos, setProgressInfos] = useState({ val: [] });
  const [message, setMessage] = useState([]);
  const [fileInfos, setFileInfos] = useState([]);
  const progressInfosRef = useRef(null);
  const [preview, setPreview] = useState(false);
  const [textToShow, setTextToShow] = useState("");



  useEffect(() => {
    UploadService.getFiles().then((response) => {
      console.log(response.data);
      setFileInfos(response.data);
    });
  }, []);

  const selectFiles = (event) => {
    setSelectedFiles(event.target.files);
    setProgressInfos({ val: [] });
  };

  const upload = (idx, file) => {
    let _progressInfos = [...progressInfosRef.current.val];
    return UploadService.upload(file, (event) => {
      _progressInfos[idx].percentage = Math.round(
        (100 * event.loaded) / event.total
      );
      setProgressInfos({ val: _progressInfos });
    })
      .then(() => {
        setMessage((prevMessage) => ([
          ...prevMessage,
          "Uploaded the file successfully: " + file.name,
        ]));
      })
      .catch(() => {
        _progressInfos[idx].percentage = 0;
        setProgressInfos({ val: _progressInfos });

        setMessage((prevMessage) => ([
          ...prevMessage,
          "Could not upload the file: " + file.name,
        ]));
      });
  };

  const uploadFiles = () => {
    const files = Array.from(selectedFiles);

    let _progressInfos = files.map(file => ({ percentage: 0, fileName: file.name }));

    progressInfosRef.current = {
      val: _progressInfos,
    }

    const uploadPromises = files.map((file, i) => upload(i, file));

    Promise.all(uploadPromises)
      .then(() => UploadService.getFiles())
      .then((files) => {
        setFileInfos(files.data);
      });

    setMessage([]);
  };

  const handleFilePreview = async (filename) => {
    console.log(filename);
    
    let result = await UploadService.getFileContent(filename);
    console.log(textToShow.data);
    setPreview(true);
    setTextToShow(result.data);
  }

  return (
    <div>
      {progressInfos && progressInfos.val.length > 0 &&
        progressInfos.val.map((progressInfo, index) => (
          <div className="mb-2" key={index}>
            <span>{progressInfo.fileName}</span>
            <div className="progress">
              <div
                className="progress-bar progress-bar-info"
                role="progressbar"
                aria-valuenow={progressInfo.percentage}
                aria-valuemin="0"
                aria-valuemax="100"
                style={{ width: progressInfo.percentage + "%" }}
              >
                {progressInfo.percentage}%
              </div>
            </div>
          </div>
        ))}

      <div className="row my-3">
        <div className="col-8">
          <label className="btn btn-default p-0">
            <input type="file" accept=".docx,.xlsx,.xlsm,.pdf" multiple onChange={selectFiles} />
          </label>
        </div>

        <div className="col-4">
          <button
            className="btn btn-success btn-sm"
            disabled={!selectedFiles}
            onClick={uploadFiles}
          >
            Upload
          </button>
        </div>
      </div>

      {message.length > 0 && (
        <div className="alert alert-secondary" role="alert">
          <ul>
            {message.map((item, i) => {
              return <li key={i}>{item}</li>;
            })}
          </ul>
        </div>
      )}

      <div className="card">
        <div className="card-header">List of Files</div>
        <table class="table table-dark">
          <thead>
            <tr>
              <th scope="col">Index</th>
              <th scope="col">FileName</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {fileInfos &&
              fileInfos.map((file, index) => (


                <tr key={index}>
                  <td>{index}</td>
                  <td>{file.name}</td>
                  <td><button type="button" className="btn btn-link" onClick={() => handleFilePreview(file.name)}>Preview</button></td>
                </tr>




              ))}
          </tbody>
        </table>
      </div>
      <div className="card">
        {preview && <div>
          <button type="button" className="btn btn-link" onClick={() => setPreview(false)}>close</button>
          <br></br>
          {textToShow}
          </div>}
      </div>
      
    </div >
  );
};

export default UploadFiles;
