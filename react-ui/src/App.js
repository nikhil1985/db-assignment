import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

import FilesUpload from "./components/FilesUpload";

function App() {
  return (
    <div className="container" style={{ width: "600px" }}>
      <div className="my-3">
        <h3>File Upload Example</h3>
        
      </div>

      <FilesUpload />
    </div>
  );
}

export default App;
