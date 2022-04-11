import React from "react";
import Col from "react-bootstrap/Col";

const AppCol = (props: any) => {
  return <Col {...props}>{props.children}</Col>;
};

export const AppColLabel = (props: any) => {
  return (
    <AppCol md={3} {...props}>
      {props.children}
    </AppCol>
  );
};

export const AppColInput = (props: any) => {
  let offset = props.colonne === 2 ? "1" : "2";

  return (
    <AppCol md={{ span: 2, offset: offset }} {...props}>
      {props.children}
    </AppCol>
  );
};

export default AppCol;
