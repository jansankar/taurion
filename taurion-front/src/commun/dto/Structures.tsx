import React from "react";


export type Echo = {
    x: number;
    y: number;
    vx: number;
    vy: number;
    type: "STA" | "FRP" | "CHA" | "TOR" | "MIS" | "WRK";
    code: string;
    iff: "ALI" | "NEU" | "HOS";
  };