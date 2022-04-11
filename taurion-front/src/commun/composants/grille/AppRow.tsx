import React from "react";
import Row from "react-bootstrap/Row";

const AppRow = (props: any) => {
  return (
    <Row style={props.style} {...props}>
      {props.children}
    </Row>
  );
};

export const AppRowInput = (props: any) => {
  return (
    <AppRow {...props} style={{ marginTop: "0px" }}>
      {props.children}
    </AppRow>
  );
};

export const AppRowLabel = (props: any) => {
  return (
    <AppRow className="label" {...props}>
      {props.children}
    </AppRow>
  );
};

export default AppRow;
