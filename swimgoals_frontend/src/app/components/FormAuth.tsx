"use client"

import useUserApi from '../hooks/useUserApi';

import AOS from 'aos';
import 'aos/dist/aos.css';
import React, { useEffect, useState } from 'react';

interface FormAuthProps {
    mainTitle: string;
}

const FormAuth: React.FC<FormAuthProps> = ({ mainTitle }) => {

    const [firstname, setFirstname] = useState<string>("");
    const [lastname, setLastname] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [role, setRole] = useState<string>("");

    const [isLogin, setIsLogin] = useState<boolean>(mainTitle === "Connexion");

    const { handleRegister, handleLogin, error } = useUserApi();

    const onSubmit = () => {
        if (isLogin){
            handleLogin({ email, password });
        } else {
            handleRegister({ firstname, lastname, email, password, role });
        }
    };

    useEffect(() => {
        AOS.init();
    })

    return (
        <div
            className="register-container"
            data-aos="fade-up"
            data-aos-duration="800"
        >
            <div className="title-container" aria-labelledby="main-title">
                <h1 className="title" id="main-title">{isLogin ? "Connexion" : "Inscription"}</h1>
            </div>
            <main className="main">
                {!isLogin && (
                    <div>
                        <fieldset className="role-buttons" aria-labelledby="role-selection">
                            <legend className="legend" id="role-selection">Choisissez votre rôle</legend>
                            <button
                                onClick={() => setRole("coach")}
                                className={role === "coach" ? "role-button active" : "role-button"}
                                aria-pressed={role === "coach"}
                            >
                                Coach
                            </button>
                            <button
                                onClick={() => setRole("swimmer")}
                                className={role === "swimmer" ? "role-button active" : "role-button"}
                                aria-pressed={role === "swimmer"}
                            >
                                Nageur
                            </button>
                        </fieldset>
                        <form
                            className="form"
                            aria-labelledby="form-title"
                        >
                            <div className="form-group">
                                <label className="name-label" htmlFor="nom">Nom</label>
                                <input
                                    type="text"
                                    className="input"
                                    placeholder="Nom"
                                    id="nom"
                                    value={lastname}
                                    onChange={(e) => setLastname(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label className="name-label" htmlFor="prenom">Prénom</label>
                                <input
                                    type="text"
                                    className="input"
                                    placeholder="Prénom"
                                    id="prenom"
                                    value={firstname}
                                    onChange={(e) => setFirstname(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label className="name-label" htmlFor="email">Adresse mail</label>
                                <input
                                    type="email"
                                    className="input"
                                    placeholder="Email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label className="name-label" htmlFor="password">Mot de passe</label>
                                <input
                                    type="password"
                                    className="input"
                                    placeholder="Mot de passe"
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                        </form>
                        {error && <p style={{ color: "red" }}>{error}</p>}
                        <button 
                            type="submit" 
                            className="submit-button"
                            onClick={onSubmit}
                        >
                            S&apos;inscrire
                        </button>
                        <footer className="footer-section">
                            <h5 className="footer-txt">
                                Déjà un compte ? <a onClick={() => setIsLogin(true)} className="txt-link">Se connecter</a>
                            </h5>
                        </footer>
                    </div>
                )}
                {isLogin && (
                    <>
                        <form
                            className="form"
                            onSubmit={onSubmit}
                        >
                            <div className="form-group">
                                <label className="name-label" htmlFor="email">Adresse mail</label>
                                <input
                                    type="email"
                                    className="input"
                                    placeholder="Email"
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label className="name-label" htmlFor="password">Mot de passe</label>
                                <input
                                    type="password"
                                    className="input"
                                    placeholder="Mot de passe"
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                        </form>
                        {error && <p style={{ color: "red" }}>{error}</p>}
                        <button
                            type="submit" 
                            className="submit-button"
                            onClick={onSubmit}
                        >
                            Se connecter
                        </button>
                        <footer className="footer-section">
                            <h5 className="footer-txt">
                                Nouveau compte ? <a onClick={() => setIsLogin(false)} className="txt-link">S&apos;inscrire</a>
                            </h5>
                        </footer>
                    </>
                )}
            </main>
        </div>
    )
}

export default FormAuth;
