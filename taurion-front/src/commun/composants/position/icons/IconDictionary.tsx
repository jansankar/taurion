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
    const nx = (20/cos);
    const ny = (20/sin)*-1;
    return <path d={`M ${cx} ${cy} L ${nx + cx} ${ny +cy} `} strokeWidth={1}/>;
}

export const ViewBoxStandard = (props:any) => {
  const {dot}:{dot:CustomDotProp} = props;
  const echo = dot.payload as Echo;

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
          {props.children}
  </svg>
  )
}

export const EchoFrpHostil = ({echo} : {echo:Echo}) => {
  return ( 
    <g transform="translate(5 10)">
    <path id="svg_1" d="M0 0 L 10 10 L20 0" />
    <circle id="svg_2" cx="10" cy="2" r="1" />
    <VecteurVDot cx={10} cy={2} vx={echo.vx} vy={echo.vy}/>
    </g>)
}

export const EchoFrpNeutre = ({echo} : {echo:Echo}) => {
  return (<g transform="translate(5 10)">
  <path d="M 0 0 L 0 10 L 20 10 L 20 0" />
  <circle id="svg_2" cx="10" cy="2" r="1" />
  <VecteurVDot cx={10} cy={2} vx={echo.vx} vy={echo.vy}/>
</g>)
}

export const EchoFrpAlie = ({echo} : {echo:Echo}) => {
  return ( <g transform="translate(5 10)">
  <path d="M 0 0 C 0 15 20 15 20 0" />
  <circle id="svg_2" cx="10" cy="2" r="1" />
  <VecteurVDot cx={10} cy={2} vx={echo.vx} vy={echo.vy}/>
  </g>);
}

export const EchoFrpDot = (dot: CustomDotProp) => {
  const echo = dot.payload as Echo;
  //<path id="svg_3" d="M 10 14 L10 20" />
      return (
        <ViewBoxStandard dot={dot}>
         {echo.iff === 'HOS' && <EchoFrpHostil echo={echo}/>}
         {echo.iff === 'NEU' && <EchoFrpNeutre echo={echo}/>}
         {echo.iff === 'ALI' && <EchoFrpAlie echo={echo}/>}
        </ViewBoxStandard>
      );
};

export const EchoCabHostil = ({echo} : {echo:Echo}) => {
return ( 
  <g>
<path id="svg_1" d="M15 5 L 25 15 L15 25 L 5 15 Z" />
<circle id="svg_2" cx="15" cy="15" r="1" />
<VecteurVDot cx={15} cy={15} vx={echo.vx} vy={echo.vy}/>
</g>
)
}

export const EchoCabNeutre = ({echo} : {echo:Echo}) => {
  return (<g>
  <path d="M 5 5 L 25 5 L 25 25 L 5 25 Z" />
  <circle id="svg_2" cx="15" cy="15" r="1" />
  <VecteurVDot cx={15} cy={15} vx={echo.vx} vy={echo.vy}/>
</g>)
}

export const EchoCabAlie = ({echo} : {echo:Echo}) => { 
  return ( <g >
  <circle cx="15" cy="15" r="10" />
  <circle id="svg_2" cx="15" cy="15" r="1" />
  <VecteurVDot cx={15} cy={15} vx={echo.vx} vy={echo.vy}/>
</g>)
}

export const EchoCabDot = (dot: CustomDotProp) => {
    const echo = dot.payload as Echo;

   return (
          <ViewBoxStandard dot={dot}>
           {echo.iff === 'HOS' && <EchoCabHostil echo={echo}/>}
           {echo.iff === 'NEU' && <EchoCabNeutre echo={echo}/>}
           {echo.iff === 'ALI' && <EchoCabAlie echo={echo}/>}
          </ViewBoxStandard>
   )
  };

export const EchoMisDot = (dot: CustomDotProp) => {
  const echo = dot.payload as Echo;

  return (
    <ViewBoxStandard dot={dot}>
        <g transform="translate(5 10)">
            <circle id="svg_2" cx="10" cy="3" r="1" />
            <text  x={7} y={3} className="recharts-text"><tspan style={{fontFamily:"monospace", fontSize:"10px", fontWeight:"100"}}>M</tspan></text>
            <VecteurVDot cx={10} cy={3} vx={echo.vx} vy={echo.vy}/>
        </g>
        <g transform="translate(0 5)">
            <path d="M 5 7 L 15 0 L 25 7"/>
        </g>
    </ViewBoxStandard>
  );};

  export const EchoTorpDot = (dot: CustomDotProp) => {
    const echo = dot.payload as Echo;
  
    return (
      <ViewBoxStandard dot={dot}>
          <g transform="translate(5 10)">
              <circle id="svg_2" cx="10" cy="3" r="1" />
              <text  x={7} y={3} className="recharts-text"><tspan style={{fontFamily:"monospace", fontSize:"10px", fontWeight:"100"}}>T</tspan></text>
              <VecteurVDot cx={10} cy={3} vx={echo.vx} vy={echo.vy}/>
          </g>
          <g transform="translate(0 5)">
              <path d="M 5 7 L 15 0 L 25 7"/>
          </g>
      </ViewBoxStandard>
    );};
