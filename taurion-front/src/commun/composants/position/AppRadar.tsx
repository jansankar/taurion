import React, { useState } from "react";
import { Echo } from "../../dto/Structures";
import AppButton from "../bouton/AppButton";
import AppCol from "../grille/AppCol";
import AppRow from "../grille/AppRow";
import AppPosition from "./AppPosition";

export const AppRadar = ({ echos, pause, activeRefresh }: { echos: Echo[], pause: any, activeRefresh: any }) => {
  const [xMin, setXMin] = useState<number>(-200);
  const [xMax, setXMax] = useState<number>(200);
  const [yMin, setYMin] = useState<number>(-200);
  const [yMax, setYMax] = useState<number>(200);
  const [choixZoom, setChoixZoom] = useState<"IN" | "OUT" | "NONE">("NONE");

  function zoomIn() {
    setChoixZoom("IN");
  }

  function zoomOut() {
    setChoixZoom("OUT");
  }

  function zoomEquals() {
    setChoixZoom("NONE");
  }

  function reinit() {
    setXMin(-200);
    setXMax(200);
    setYMin(-200);
    setYMax(200);
    setChoixZoom("NONE");
  }

  function resize(posX: number, posY: number) {
    const clicX = Math.round(posX);
    const clicY = Math.round(posY);

    let xWidth = xMax - xMin;
    let yWidth = yMax - yMin;

    if (choixZoom === "IN") {
      xWidth = Math.round(xWidth / 2);
      yWidth = Math.round(yWidth / 2);
    }

    if (choixZoom === "OUT") {
      xWidth = Math.round(xWidth * 2);
      yWidth = Math.round(yWidth * 2);
    }

    setXMin(clicX - xWidth / 2);
    setXMax(clicX + xWidth / 2);

    setYMin(clicY - yWidth / 2);
    setYMax(clicY + yWidth / 2);
  }

  return (
    <AppRow>
      <AppCol md={12} style={{minWidth: '460px'}}>
        <div style={{position: "relative", float: "left"}}>
        <AppPosition
          echos={echos}
          xMin={xMin}
          xMax={xMax}
          yMin={yMin}
          yMax={yMax}
          resize={resize}
        />
        </div>
        <div style={{width: '50px', float:"left", position: "relative", marginLeft: "-20px"}}>
        <div style={{marginTop:'20px'}}>
          <AppButton onClick={zoomIn} actif={choixZoom === "IN"}>
            +
          </AppButton>
        </div>
        <div style={{marginTop:'10px'}}>
          <AppButton onClick={zoomOut} actif={choixZoom === "OUT"}>
            -
          </AppButton>
        </div>

        <div style={{marginTop:'10px'}}>
          <AppButton onClick={zoomEquals} actif={choixZoom === "NONE"}>
            =
          </AppButton>
        </div>

        <div style={{marginTop:'10px'}}>
          <AppButton onClick={reinit}>O</AppButton>
        </div>
        <div style={{marginTop:'10px'}}>
          <AppButton onClick={pause}>II</AppButton>
        </div>
        <div style={{marginTop:'10px'}}>
          <AppButton onClick={activeRefresh}>&gt;</AppButton>
        </div>
        </div>
      </AppCol>
      <AppCol md={{span: 1}} style={{marginLeft: '-30px'}}>
        
      </AppCol>
    </AppRow>
  );
};
