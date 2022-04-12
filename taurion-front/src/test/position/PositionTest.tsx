import { countReset } from "console";
import React, { useEffect, useRef, useState } from "react";
import AppCol from "../../commun/composants/grille/AppCol";
import AppRow from "../../commun/composants/grille/AppRow";
import AppPosition from "../../commun/composants/position/AppPosition";
import { AppRadar } from "../../commun/composants/position/AppRadar";
import { Echo } from "../../commun/composants/position/icons/IconDictionary";

const contactTempList = (compteur: number): Echo[] => {
  return [
    {
      x: 10 + compteur * 10,
      y: 10 + compteur * 3,
      vx: 0,
      vy: 0,
      code: "I01",
      iff: "NEU",
      type: "TOR",
    },
    {
      x: 120 - compteur * 4,
      y: -80 - compteur * 2,
      vx: 3,
      vy: 1,
      code: "I02",
      iff: "ALI",
      type: "FRP",
    },
    { x: -30, y: 25, vx: 0, vy: 0, code: "I03", iff: "HOS", type: "STA" },
    { x: -80, y: -25, vx: 0, vy: 0, code: "M01", iff: "HOS", type: "MIS" },
  ];
};

export const PositionTest = () => {
  const [contact, setContact] = useState<Echo[]>([]);
  const compteur = useRef(0);

  useEffect(() => {
    if (compteur.current < 1) {
      compteur.current += 1;
      const timer = setTimeout(
        () => setContact(contactTempList(compteur.current)),
        1000
      );
      return () => clearTimeout(timer);
    }
  }, [contact]);

  return (
    <div>
      <AppRow>
        <AppCol md={4}>
          <AppRadar echos={contact} />
        </AppCol>
        <AppCol md={6}></AppCol>
      </AppRow>
      <AppRow>
        <AppCol md={12}></AppCol>
      </AppRow>
    </div>
  );
};
