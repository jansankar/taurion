import { countReset } from "console";
import React, { useEffect, useRef, useState } from "react";
import AppCol from "../../commun/composants/grille/AppCol";
import AppRow from "../../commun/composants/grille/AppRow";
import { AppRadar } from "../../commun/composants/position/AppRadar";
import { Echo } from "../../commun/dto/Structures";

const contactTempList = (compteur: number): Echo[] => {
  return [

    { x: -150, y: 10, vx: 0, vy: 5, code: "C01", iff: "HOS", type: "CAB" },
    { x: -100, y: 10, vx: 5, vy: 5, code: "C02", iff: "NEU", type: "CAB" },
    { x: -50, y: 10, vx: 5, vy: 0, code: "C03", iff: "ALI", type: "CAB" },

    { x: 50, y: 10, vx: 0, vy: 5, code: "T01", iff: "HOS", type: "TOR" },
    { x: 100, y: 10, vx: 5, vy: 5, code: "T02", iff: "NEU", type: "TOR" },
    { x: 150, y: 10, vx: 5, vy: 0, code: "T03", iff: "ALI", type: "TOR" },

    { x: -150, y: -30, vx: 4, vy: -4, code: "M02", iff: "HOS", type: "MIS" },
    { x: -100, y: -30, vx: 0, vy: -4, code: "M03", iff: "NEU", type: "MIS" },
    { x: -50, y: -30, vx: -4, vy: -4, code: "M04", iff: "ALI", type: "MIS" },

    { x: 50, y: -30, vx: 4, vy: -4, code: "F01", iff: "HOS", type: "FRP" },
    { x: 100, y: -30, vx: 0, vy: -4, code: "F02", iff: "NEU", type: "FRP" },
    { x: 150, y: -30, vx: -4, vy: -4, code: "F03", iff: "ALI", type: "FRP" },

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
