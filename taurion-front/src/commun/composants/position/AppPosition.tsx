import React from "react";
import {
  ScatterChart,
  Scatter,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  LabelList,
} from "recharts";
import { Echo, EchoFrpDot, EchoMisDot, getColor } from "./icons/IconDictionary";

export type AppPositionProps = {
  echos: Echo[];
};

const CustomizedDot = (props: any) => {
  const { cx, cy, stroke, payload, value } = props;
  const echo = payload as Echo;

  switch (echo.type) {
    case "FRP":
      return <EchoFrpDot {...props} />;
    case "MIS":
      return <EchoMisDot {...props} />;
  }

  return <EchoFrpDot {...props} />;
};

const CustomTooltip = (props: any) => {
  const { active, payload, label }: { active: any; payload: any; label: any } =
    props;

  if (active && payload && payload.length) {
    const echo = payload[0].payload as Echo;
    return (
      <div className="custom-tooltip" style={{ color: getColor(echo) }}>
        {echo.code}
      </div>
    );
  }

  return null;
};

export default function AppPosition({ echos }: AppPositionProps) {
  return (
    <ScatterChart
      width={400}
      height={400}
      margin={{
        top: 20,
        right: 20,
        bottom: 20,
        left: 20,
      }}
    >
      <CartesianGrid />
      <XAxis type="number" dataKey="x" domain={[-200, 200]} />
      <YAxis type="number" dataKey="y" domain={[-200, 200]} />
      <Tooltip
        cursor={{ strokeDasharray: "3 3" }}
        content={<CustomTooltip />}
      />
      <Scatter
        data={echos}
        fill="#23d18b"
        isAnimationActive={true}
        animationDuration={1000}
        shape={<CustomizedDot />}
      ></Scatter>
    </ScatterChart>
  );
}
