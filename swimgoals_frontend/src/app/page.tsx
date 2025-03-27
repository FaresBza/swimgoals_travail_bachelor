"use client"

import { useState, useEffect, useRef } from "react";
import AOS from "aos";
import "aos/dist/aos.css";

import ButtonAuth from "./components/ButtonAuth";
import Header from "./components/Header";
import FormAuth from "./components/FormAuth";

import './styles/Auth.scss';
import './styles/BackgroundImage.scss';
import './styles/FormAuth.scss';

export default function Home() {
  const [openForm, setOpenForm] = useState(false);
  const [formText, setFormText] = useState("");
  const [isVisible, setIsVisible] = useState(false);
  const formRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    AOS.init();
  }, []);

  useEffect(() => {
    const handleClickOutsideToCloseForm = (event: MouseEvent) => {
      if (
        openForm &&
        formRef.current &&
        !formRef.current.contains(event.target as Node)
      ) {
        setIsVisible(false);
        setTimeout(() => setOpenForm(false), 500);
      }
    };

    document.addEventListener("mousedown", handleClickOutsideToCloseForm);
    return () => document.removeEventListener("mousedown", handleClickOutsideToCloseForm);
  }, [openForm]);

  return (
    <div className="container auth">
      <Header />
      <main className="home-main">
        <div className="button-container">
          {!openForm && (
            <>
              <ButtonAuth 
                txt="Connexion" 
                onClick={() => {
                  setOpenForm(true);
                  setFormText("Connexion");
                  setIsVisible(true);
                }}
              />
              <ButtonAuth 
                txt="Inscription" 
                onClick={() => {
                  setOpenForm(true);
                  setFormText("Inscription");
                  setIsVisible(true);
                }}
              />
            </>
          )}
        </div>
      </main>
      <footer className="footer">
        {openForm && (
          <div 
            ref={formRef} 
            className={`form-container ${isVisible ? "fade-in" : "fade-out"}`}
          >
            <FormAuth mainTitle={formText} />
          </div>
        )}
      </footer>
    </div>
  );
}
