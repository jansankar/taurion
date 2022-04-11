import React from "react";
import { useNavigate } from "react-router-dom";

export type MenuGaucheItems = {
  code: string;
  lien: string;
  parent?: string | string[];
};

export type AppMenuGaucheProps = {
  elements: MenuGaucheItems[];
};

export default function AppMenuGauche(props: AppMenuGaucheProps) {
  const navigate = useNavigate();
  const elements = props.elements;
  
  return (
    <>
      {elements.map((element) => (
        <div key="{element}" onClick={() => navigate(element.lien)}>{element.code}</div>
      ))}
    </>
  );
}
