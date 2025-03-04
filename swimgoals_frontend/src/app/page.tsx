"use client"

import { useState } from "react";

import ButtonAuth from "./components/ButtonAuth";
import Header from "./components/Header";
import FormAuth from "./components/FormAuth";

import './styles/Home.scss'
import './styles/BackgroundImage.scss'
import './styles/FormAuth.scss'

export default function Home() {

  const [openForm, setOpenForm] = useState<boolean>(false);
  const [formText, setFormText] = useState<string>("");
  const [formButtonText, setFormButtonText] = useState<string>("");

  return (
    <div className="container">
      <Header />
      <main className="home-main">
        <div className="button-container">
          <ButtonAuth 
            txt="Connexion" 
            onClick={() => {
              setOpenForm(true);
              setFormText("Connexion")
              setFormButtonText("Se connecter")
            }}
            />
          <ButtonAuth 
            txt="Inscription" 
            onClick={() => {
              setOpenForm(true)
              setFormText("Inscription")
              setFormButtonText("S'inscrire")
            }}
            />
        </div>
      </main>
      <footer className="footer">
        {openForm && (
          <FormAuth 
            mainTitle={formText}
            buttonTitle={formButtonText}
          />
        )}
      </footer>
    </div>
  )
}
