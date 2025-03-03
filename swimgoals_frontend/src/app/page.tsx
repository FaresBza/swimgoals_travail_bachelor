"use client"

import { useState } from "react";

import ButtonAuth from "./components/ButtonAuth";
import Header from "./components/Header";

import './styles/Home.scss'
import './styles/BackgroundImage.scss'
import FormAuth from "./components/FormAuth";

export default function Home() {

  const [openForm, setOpenForm] = useState(false);

  return (
    <div className="container">
      <Header />
      <main className="home-main">
        <div className="button-container">
          <ButtonAuth 
            txt="Connexion" 
            onClick={() => setOpenForm(true)}
            />
          <ButtonAuth 
            txt="Inscription" 
            onClick={() => setOpenForm(true)}
            />
        </div>
        {openForm && (
          <FormAuth />
        )}
      </main>
    </div>
  )
}
