import React, { useState } from "react";
import AppButton from "../bouton/AppButton";
import AppCol from "../grille/AppCol";
import AppRow from "../grille/AppRow";
import AppPosition from "./AppPosition";
import { Echo } from "./icons/IconDictionary";

export const AppRadar = ({ echos }: { echos: Echo[] }) => {
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
      <AppCol md={11}>
        <AppPosition
          echos={echos}
          xMin={xMin}
          xMax={xMax}
          yMin={yMin}
          yMax={yMax}
          resize={resize}
        />
      </AppCol>
      <AppCol md={{span: 1}} style={{marginLeft: '-30px'}}>
        <AppRow style={{marginTop:'20px'}}>
          <AppButton onClick={zoomIn} actif={choixZoom === "IN"}>
            +
          </AppButton>
        </AppRow>
        <AppRow style={{marginTop:'10px'}}>
          <AppButton onClick={zoomOut} actif={choixZoom === "OUT"}>
            -
          </AppButton>
        </AppRow>

        <AppRow style={{marginTop:'10px'}}>
          <AppButton onClick={zoomEquals} actif={choixZoom === "NONE"}>
            =
          </AppButton>
        </AppRow>

        <AppRow style={{marginTop:'10px'}}>
          <AppButton onClick={reinit}>O</AppButton>
        </AppRow>
      </AppCol>
    </AppRow>
  );
};
