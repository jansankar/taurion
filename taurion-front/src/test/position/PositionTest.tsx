import { countReset } from "console";
import React, { useEffect, useRef, useState } from "react";
import AppCol from "../../commun/composants/grille/AppCol";
import AppRow from "../../commun/composants/grille/AppRow";
import { AppRadar } from "../../commun/composants/position/AppRadar";
import { Echo } from "../../commun/dto/Structures";

const contactTempList = (compteur: number): Echo[] => {
  return [
    {
      x: 10 + compteur * 1.5,
      y: 10 + compteur * 3,
      vx: 1.5,
      vy: 3,
      code: "F11",
      iff: "NEU",
      type: "FRP",
    },
    {
      x: 120 - compteur * 1.5,
      y: -80 - compteur * 2,
      vx: 1.5,
      vy: 2,
      code: "F01",
      iff: "ALI",
      type: "FRP",
    },
    { x: -30, y: 25, vx: -4, vy: -1, code: "S01", iff: "HOS", type: "STA" },
    { x: 40, y: 74, vx: -4, vy: -1, code: "T02", iff: "NEU", type: "TOR" },
    { x: -80, y: -25, vx: 10, vy: 4, code: "M01", iff: "HOS", type: "MIS" },
  ];
};

export const PositionTest = () => {
  const [contact, setContact] = useState<Echo[]>([]);
  const [refreshActif, setRefresh] = useState<boolean>(false);
  const compteur = useRef(0);

  useEffect(() => {
      if (!refreshActif)
        setContact(contactTempList(compteur.current));
  }, []);

  useEffect(() => {
    if (refreshActif) {
      compteur.current += 1;
      const timer = setTimeout(
        () => setContact(contactTempList(compteur.current)),
        3000
      );
      return () => clearTimeout(timer);
    }
  }, [contact, refreshActif]);

  function pause() {
    setRefresh(false);
  }

  function refresh() {
    setRefresh(true);
  }

  return (
    <div>
      <AppRow>
        <AppCol md={4}>
          <AppRadar echos={contact} pause={pause} activeRefresh={refresh} />
        </AppCol>
        <AppCol md={6}></AppCol>
      </AppRow>
      <AppRow>
        <AppCol md={12}></AppCol>
      </AppRow>
    </div>
  );
};
