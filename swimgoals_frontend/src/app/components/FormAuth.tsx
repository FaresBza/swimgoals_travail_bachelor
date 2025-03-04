"use client"

import useUserApi from '../hooks/useUserApi';

import AOS from 'aos';
import 'aos/dist/aos.css';
import React, { useEffect, useState } from 'react';

interface FormAuthProps {
    mainTitle: string;
    buttonTitle: string;
}

const FormAuth: React.FC<FormAuthProps> = ({ mainTitle, buttonTitle }) => {

    const [firstname, setFirstname] = useState<string>("");
    const [lastname, setLastname] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [role, setRole] = useState<string>("");

    const [isLogin, setIsLogin] = useState<boolean>(mainTitle === "Connexion");
    const [isButtonLogin] = useState<boolean>(buttonTitle === "Se connecter");

    const { handleRegister, handleLogin } = useUserApi();

    const onSubmit = () => {
        if (mainTitle === "Connexion"){
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
            data-aos-duration="500"
        >
            <div className="title-container">
                <h1 className="title">{isLogin ? "Connexion" : "Inscription"}</h1>
            </div>
            <main className="main">
                {!isLogin && (
                    <div>
                        <div className="role-buttons">
                            <button
                                onClick={() => setRole("admin")}
                                className={role === "admin" ? "active" : ""}
                            >
                                Admin
                            </button>
                            <button
                                onClick={() => setRole("coach")}
                                className={role === "coach" ? "active" : ""}
                            >
                                Coach
                            </button>
                            <button
                                onClick={() => setRole("swimmer")}
                                className={role === "swimmer" ? "active" : ""}
                            >
                                Nageur
                            </button>
                        </div>
                        <form
                            className="form"
                        >
                            <div className="form-group">
                                <label>Nom</label>
                                <input
                                    type="text"
                                    placeholder="Nom"
                                    value={lastname}
                                    onChange={(e) => setLastname(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Prénom</label>
                                <input
                                    type="text"
                                    placeholder="Prénom"
                                    value={firstname}
                                    onChange={(e) => setFirstname(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Adresse mail</label>
                                <input
                                    type="email"
                                    placeholder="Email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Mot de passe</label>
                                <input
                                    type="password"
                                    placeholder="Mot de passe"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                        </form>
                        <button 
                            type="submit" 
                            className="submit-button"
                            onClick={onSubmit}
                        >
                            {isButtonLogin ? "S'inscrire" : "Se connecter"}
                        </button>
                        <footer>
                            <h5 className='footer-txt'>
                                Déjà un compte ? <a onClick={() => setIsLogin(true)}>Se connecter</a>
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
                                <label>Adresse mail</label>
                                <input
                                    type="email"
                                    placeholder="Email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label>Mot de passe</label>
                                <input
                                    type="password"
                                    placeholder="Mot de passe"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                        </form>
                        <button
                            type="submit" 
                            className="submit-button"
                            onClick={onSubmit}
                        >
                            {isButtonLogin ? "Se connecter" : "S'inscrire"}
                        </button>
                        <footer>
                            <h5 className='footer-txt'>
                                Nouveau compte ? <a onClick={() => setIsLogin(false)}>S&apos;inscrire</a>
                            </h5>
                        </footer>
                    </>
                )}
            </main>
        </div>
    )
}

export default FormAuth;
