import React, { useState } from "react";
import { Button } from "react-bootstrap";
import AppPosition from "./AppPosition";
import styles from './AppRadar.module.scss';
import { Echo } from "./icons/IconDictionary";

export const AppRadar = ({echos} : {echos:Echo[]}) => {
    const [xMin, setXMin] = useState<number>(-200);
    const [xMax, setXMax] = useState<number>(200);
    const [yMin, setYMin] = useState<number>(-200);
    const [yMax, setYMax] = useState<number>(200);
    const [choixZoom, setChoixZoom] = useState<"IN"|"OUT"|"NONE">("NONE");

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

    function resize(posX : number, posY:number) {
        const clicX = Math.round(posX);
        const clicY = Math.round(posY);

        let xWidth = xMax - xMin;
        let yWidth = yMax - yMin;

        if (choixZoom == 'IN') {
            xWidth = Math.round(xWidth/2);
            yWidth = Math.round(yWidth/2);
        }

        if (choixZoom == 'OUT') {
            xWidth = Math.round(xWidth*2);
            yWidth = Math.round(yWidth*2);
        }

        setXMin(clicX - xWidth/2);
        setXMax(clicX + xWidth/2);

        setYMin(clicY - yWidth/2);
        setYMax(clicY + yWidth/2);
    }

    return (
        <>
        <AppPosition echos={echos} xMin={xMin} xMax={xMax} yMin={yMin} yMax={yMax} resize={resize}/>
        <div style={{marginLeft: "70px"}}>
        <Button onClick={zoomIn} className={styles.Button} style={choixZoom == "IN" ? {borderColor: "#5afc75"} : {}}>+</Button> 
        <Button onClick={zoomOut} className={styles.Button} style={choixZoom == "OUT" ? {borderColor: "#5afc75"} : {}}>-</Button>
        <Button onClick={zoomEquals} className={styles.Button} style={choixZoom == "NONE" ? {borderColor: "#5afc75"} : {}}>=</Button>
        <Button onClick={reinit} className={styles.Button}>O</Button>
        </div>
        </>
    );
}