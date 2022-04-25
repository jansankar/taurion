import React from "react";


export type Echo = {
    x: number;
    y: number;
    vx: number;
    vy: number;
    type: "STA" | "FRP" | "CHA" | "TOR" | "MIS" | "WRK" | "CAB";
    code: string;
    iff: "ALI" | "NEU" | "HOS";
  };