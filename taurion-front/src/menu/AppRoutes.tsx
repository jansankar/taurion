import React from "react";
import { Route, Routes } from "react-router-dom";
import {PositionTest} from "../test/position/PositionTest";

const Home = () => {
  return <h2>Home</h2>;
};

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/test-depl" element={<PositionTest/>}/>
    </Routes>
  );
};
