import React, { useEffect, useState } from "react";
import AOS from 'aos';
import 'aos/dist/aos.css';
import useGroupApi from "../hooks/useGroupApi";
import useLocalStorage from "../hooks/useLocalStorage";

const FormGroup = () => {
    const [name, setName] = useState<string>("");

    const { createGroup, error } = useGroupApi();
    const { coachId, recoverUserId } = useLocalStorage();

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        createGroup({ coachId, name });
    };


    useEffect(() => {
        AOS.init();
        recoverUserId();
    })

    return(
        <div
            className="add-group-container"
            data-aos="fade-up"
            data-aos-duration="800"
        >
            <main className="main-container">
                <div className="add-group-card">
                    <h2 className="title-card">Ajouter un groupe</h2>
                    <form
                        className="form-add-group"
                        onSubmit={onSubmit}
                    >
                        <label className="label-group" htmlFor="group-name">Nom</label>
                        <input
                            type="text"
                            id="group-name"
                            className="input-group"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="Triathlon"
                        />
                        {error && <p style={{ color: "red" }}>{error}</p>}
                        <button type="submit">Ajouter</button>
                    </form>
                </div>
            </main>
        </div>
    )
}

export default FormGroup;