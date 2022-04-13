import React, { useEffect, useRef, useState } from "react";
import { Echo } from "../../../dto/Structures";


const tailleElement = {
    viewPortX: 30,
    viewPortY: 30,
}

const positionColorTheme = {
  ALI: "#23d18b",
  NEU: "yellow",
  HOS: "red",
};

export function getColor(echo: Echo) {
  const colorType = echo ? echo.iff : "NEU";
  return positionColorTheme[colorType];
}

export type CustomDotProp = {
  cx: number;
  cy: number;
  stroke?: any;
  payload?: Echo;
  value?: any;
};

/*export const SelectorDot = (dot: CustomDotProp) => {
    return          
    <path id="svg3" d="M 0 0 L 20 0 L 20 20 L 0 20 Z" stroke="blue" strokeWidth={1}/>
    <path id="svg3" d="M 0 10 L 20 10 M 10 0 L 10 20" stroke="blue" strokeWidth={1}/>

}*/

export const VecteurVDot = ({cx, cy, vx, vy} : {cx:number, cy:number,vx:number, vy:number}) => {
    if (vx === 0 && vy ===0)
    return <></>;

    const h = Math.hypot(vx, vy);
    const cos = h/vx;
    const sin = h/vy;
    const nx = (30/cos);
    const ny = (30/sin)*-1;
    return <path d={`M ${cx} ${cy} L ${nx + cx} ${ny +cy} `} strokeWidth={1}/>;
}

export const EchoFrpDot = (dot: CustomDotProp) => {
  const echo = dot.payload as Echo;
  //<path id="svg_3" d="M 10 14 L10 20" />
  switch (echo.iff) {
    case "HOS":
      return (
        <svg
          x={dot.cx - 15}
          y={dot.cy - 15}
          width={30}
          height={30}
          fill="transparent"
          strokeWidth={2}
          viewBox="0 0 30 30"
          stroke={getColor(echo)}
        >
          <g transform="translate(5 10)">
            <path id="svg_1" d="M0 0 L 10 10 L20 0" />
            <circle id="svg_2" cx="10" cy="2" r="1" />
            <VecteurVDot cx={10} cy={2} vx={echo.vx} vy={echo.vy}/>
          </g>
        </svg>
      );
    case "NEU":
      return (
        <svg
          x={dot.cx - 15}
          y={dot.cy - 15}
          width={30}
          height={30}
          fill="transparent"
          strokeWidth={2}
          viewBox="0 0 30 30"
          stroke={getColor(echo)}
        >
          <g transform="translate(5 10)">
            <path d="M 2 0 L 2 10 L 18 10 L 18 0" />
            <circle id="svg_2" cx="10" cy="4" r="1" />
            <VecteurVDot cx={10} cy={4} vx={echo.vx} vy={echo.vy}/>
          </g>
        </svg>
      );
    case "ALI":
      return (
        <svg
          x={dot.cx - 15}
          y={dot.cy - 15}
          width={30}
          height={30}
          fill="transparent"
          strokeWidth={2}
          viewBox="0 0 30 30"
          stroke={getColor(echo)}
        >
          <g transform="translate(5 10)">
            <path d="M 2 2 C 2 13 18 13 18 2" />
            <circle id="svg_2" cx="10" cy="4" r="1" />
            <VecteurVDot cx={10} cy={4} vx={echo.vx} vy={echo.vy}/>
          </g>
        </svg>
      );
  }
};

export const EchoCabDot = (dot: CustomDotProp) => {
    const echo = dot.payload as Echo;
    //<path id="svg_3" d="M 10 14 L10 20" />
    switch (echo.iff) {
      case "HOS":
        return (
          <svg
            x={dot.cx - 15}
            y={dot.cy - 15}
            width={30}
            height={30}
            fill="transparent"
            strokeWidth={2}
            viewBox="0 0 30 30"
            stroke={getColor(echo)}
          >
            <g transform="translate(5 10)">
              <path id="svg_1" d="M0 0 L 10 10 L20 0" />
              <circle id="svg_2" cx="10" cy="2" r="1" />
              <VecteurVDot cx={10} cy={2} vx={echo.vx} vy={echo.vy}/>
            </g>
          </svg>
        );
      case "NEU":
        return (
          <svg
            x={dot.cx - 15}
            y={dot.cy - 15}
            width={30}
            height={30}
            fill="transparent"
            strokeWidth={2}
            viewBox="0 0 30 30"
            stroke={getColor(echo)}
          >
            <g transform="translate(5 10)">
              <path d="M 2 0 L 2 10 L 18 10 L 18 0" />
              <circle id="svg_2" cx="10" cy="4" r="1" />
              <VecteurVDot cx={10} cy={4} vx={echo.vx} vy={echo.vy}/>
            </g>
          </svg>
        );
      case "ALI":
        return (
          <svg
            x={dot.cx - 10}
            y={dot.cy - 10}
            width={20}
            height={20}
            fill="transparent"
            strokeWidth={2}
            viewBox="0 0 20 20"
            stroke={getColor(echo)}
          >
            <g transform="translate(0 5)">
              <path d="M 2 2 C 2 13 18 13 18 2" />
              <circle id="svg_2" cx="10" cy="4" r="1" />
              <VecteurVDot cx={10} cy={4} vx={echo.vx} vy={echo.vy}/>
            </g>
          </svg>
        );
    }
  };

export const EchoMisDot = (dot: CustomDotProp) => {
  const echo = dot.payload as Echo;

  return (
    <svg
      x={dot.cx - 15}
      y={dot.cy - 15}
      width={30}
      height={30}
      fill="transparent"
      stroke={getColor(echo)}
      strokeWidth={2}
      viewBox="0 0 30 30"
    >
        <g transform="translate(5 10)">
            <circle id="svg_2" cx="10" cy="3" r="1" />
            <VecteurVDot cx={10} cy={3} vx={echo.vx} vy={echo.vy}/>
        </g>
        <g transform="scale(0.7) translate(11 7)">
            <path d="M 0 15 L 7 5 L 13 5 L 20 15"/>
        </g>
    </svg>
  );};

  export const EchoTorpDot = (dot: CustomDotProp) => {
    const echo = dot.payload as Echo;
  
    return (
      <svg
        x={dot.cx - 15}
        y={dot.cy - 15}
        width={30}
        height={30}
        fill="transparent"
        stroke={getColor(echo)}
        strokeWidth={2}
        viewBox="0 0 30 30"
      >
           <g transform="translate(5 10)">
            <circle id="svg_2" cx="10" cy="3" r="1" />
            <VecteurVDot cx={10} cy={3} vx={echo.vx} vy={echo.vy}/>
        </g>
        <g transform="scale(0.7) translate(11 8)">
            <path d="M 0 15 L 7 5 L 13 5 L 20 15 Z"/>
        </g>
      </svg>
    );
};
