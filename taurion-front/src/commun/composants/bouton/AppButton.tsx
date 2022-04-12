import React from "react";
import { Button } from "react-bootstrap";
import styles from './AppButton.module.scss';

export default function AppButton(props:any) {
    const active = props.actif ? props.actif : false as boolean;
    let classNameConcat = styles.push ;
    
    if (active) {
        classNameConcat += ' ' + styles.active;
    }

    return (<Button {...props} className={classNameConcat}/>)
}