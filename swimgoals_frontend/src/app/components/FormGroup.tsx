import React, { useEffect, useState } from "react";
import AOS from 'aos';
import 'aos/dist/aos.css';
import useGroupApi from "../hooks/useGroupApi";

const FormGroup = () => {
    const [name, setName] = useState<string>("");
    const [coachId, setCoachId] = useState<number>(0);

    const { createGroup } = useGroupApi();

    const recoverCoachId = () => {
        const storedUser = localStorage.getItem("user");

        if (!storedUser) {
            console.error("Aucun utilisateur trouvé dans le localStorage");
            return;
        }

        const user = JSON.parse(storedUser);
        if (user && user.roleId === 2) {
            setCoachId(user.id);
        } else {
            console.error("Utilisateur non valide ou non connecté");
        }
    };

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        createGroup({ name, coachId });
    };


    useEffect(() => {
        AOS.init();
        recoverCoachId();
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
                            required
                        />
                        <button type="submit">Ajouter</button>
                    </form>
                </div>
            </main>
        </div>
    )
}

export default FormGroup;