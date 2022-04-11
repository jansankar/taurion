import React, { useEffect, useRef, useState } from "react";
import AppCol from "../../commun/composants/grille/AppCol";
import AppRow from "../../commun/composants/grille/AppRow";
import AppPosition, { Coordonnee } from "../../commun/composants/position/AppPosition";


export const PositionTest = () => {
    const [contact, setContact] = useState({x:15, y:15, code:'i001'});
    const compteur = useRef(0);

    useEffect(() => {
        if (compteur.current < 10) {
            compteur.current+=1;
            const timer = setInterval(() => setContact({ x: contact.x + 10, y: contact.y +5, code: contact.code }), 1000);
            return () => clearInterval(timer);
        }
    }, [contact]);

    return (
    <div>
        <AppRow>
            <AppCol span="6">
                <AppPosition coordonnees={contact}/>
            </AppCol> 
            <AppCol span="6"></AppCol>
        </AppRow>
        <AppRow>
            <AppCol span="12"></AppCol>
        </AppRow>
    </div>
    );
}