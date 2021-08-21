import http from "../http-common";

const upload = (file, onUploadProgress) => {
  let formData = new FormData();

  formData.append("file", file);

  return http.post("/upload", formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
    onUploadProgress,
  });
};

const getFiles = () => {
  return http.get("/files");
};

const getFileContent = async (fileName) => {
  console.log(fileName);
  return http.get("/getFile/"+fileName);
};

const FileUploadService = {
  upload,
  getFiles,
  getFileContent
};

export default FileUploadService; 
