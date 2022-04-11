import React from 'react';
import styles from './App.module.scss';
import AppCol from './commun/composants/grille/AppCol';
import AppRow from './commun/composants/grille/AppRow';
import { BrowserRouter as Router } from "react-router-dom";

import AppMenuGauche, { AppMenuGaucheProps, MenuGaucheItems } from './menu/AppMenuGauche';
import AppRoutes from './menu/AppRoutes';

const useAppMenuGaucheItems = () => {
  const elements: MenuGaucheItems[] = [];
  elements.push({code:"test-depl", lien: "test-depl"});
  elements.push({code:"test-depl-01", lien: "test-depl"});
  return elements;
}

function App() {
const menuGaucheItems = useAppMenuGaucheItems() as MenuGaucheItems[];

  return (
    <Router>
    <div className={styles.App}>
      <AppRow>
        <AppCol md ={{span : 1}} >
          <AppMenuGauche elements={menuGaucheItems}/>
        </AppCol>
        <AppCol md ={{span : 11}} >
         <AppRoutes />
        </AppCol>
      </AppRow>
    </div>
    </Router>
  );
}

export default App;