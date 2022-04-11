import React from "react";
import {
  ScatterChart,
  Scatter,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  LabelList
} from "recharts";

const data = [
  { x: 100, y: 200, z: 200 },
  { x: 120, y: 100, z: 260 },
  { x: 170, y: 300, z: 400 },
  { x: 140, y: 250, z: 280 },
  { x: 150, y: 400, z: 500 },
  { x: 110, y: 280, z: 200 }
];

export type AppPositionProps = {
    coordonnees: Coordonnee;
}

export type Coordonnee = {
    x: number;
    y: number;
    code: string;
  };

export default function AppPosition({coordonnees} : AppPositionProps) {
    const data:Coordonnee[] = [];
    data.push(coordonnees);

  return (
    <ScatterChart
      width={400}
      height={400}
      margin={{
        top: 20,
        right: 20,
        bottom: 20,
        left: 20
      }}
    >
      <CartesianGrid />
      <XAxis type="number" dataKey="x" domain={[-200, 200]} />
      <YAxis type="number" dataKey="y" domain={[-200, 200]} />
      <Tooltip cursor={{ strokeDasharray: "3 3" }} />
      <Scatter name="A school" data={data} fill="#23d18b">
        <LabelList dataKey="code" position="bottom" fill="#23d18b"/>
      </Scatter>
    </ScatterChart>
  );
}
