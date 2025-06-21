import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddStudent from "./students/AddUser";
import EditStudent from "./students/EditUser";
import ViewStudent from "./students/ViewUser";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />

        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/adduser" element={<AddStudent />} />
          <Route exact path="/edituser/:id" element={<EditStudent />} />
          <Route exact path="/viewuser/:id" element={<ViewStudent />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
