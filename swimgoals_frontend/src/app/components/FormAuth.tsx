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

    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");

    const { handleRegister } = useUserApi();

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (mainTitle === "Connexion"){
            console.log("Login")
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
                <h1 className="title">{mainTitle}</h1>
            </div>
                <main className="main">
                    {mainTitle === "Inscription" && (
                        <>
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
                            onSubmit={onSubmit}
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
                                <button type="submit" className="submit-button">{buttonTitle}</button>
                            </form>
                        </>
                    )}
                    {mainTitle === "Connexion" && (
                        <form
                            className="form"
                            onSubmit={onSubmit}
                        >
                            <div className="form-group">
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
                                <button type="submit" className="submit-button">{buttonTitle}</button>
                            </div>
                        </form>
                    )}
            </main>
        </div>
    )
}

export default FormAuth;