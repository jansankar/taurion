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

export type AppPositionProps = {
    coordonnees: Coordonnee;
}

export type Coordonnee = {
    x: number;
    y: number;
    code: string;
  };

  const CustomizedDot = (props:any) => {
    const { cx, cy, stroke, payload, value } = props;

    return (
      <svg x={cx - 10} y={cy - 10} width={20} height={25} 
      fill="transparent" 
      stroke="#23d18b" 
      strokeWidth={2}
      viewBox="0 0 20 20">
           <path id="svg_1" d="M0 0 L 10 10 L20 0" />
           <circle id="svg_2" cx="10" cy="2" r="1" fill="#23d18b" />
           <text x="0" y="22" strokeWidth={1} style={{fontSize: '12', fontWeight: "lighter", fontFamily:"monospace"}}>{payload.code}</text> 
      </svg>
    );
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
      <Tooltip cursor={{ strokeDasharray: "3 3" }}/>
      <Scatter data={data} fill="#23d18b"  isAnimationActive={true} animationDuration={1000}  shape={<CustomizedDot />} >
      </Scatter>
    </ScatterChart>
  );
}
