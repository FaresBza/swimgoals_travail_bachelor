"use client";

import { useEffect, useState } from "react";
import { useRouter, useParams } from "next/navigation";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";
import useLocalStorage from "@/app/hooks/useLocalStorage";
import SwimMapping from "@/app/mapping/SwimMapping";

import './../../styles/FormObjective.scss'

const FormObjective = () => {
    const router = useRouter();
    const { id } = useParams(); // id du nageur

    const { createObjective } = useObjectiveApi();
    const { recoverUserId } = useLocalStorage();

    const [selectedSwim, setSelectedSwim] = useState<string>("");
    const [distance, setDistance] = useState<string>("");
    const [time, setTime] = useState<string>("");

    useEffect(() => {
        recoverUserId();
    }, []);

    const addObjective = () => {
        if (!selectedSwim || !distance || !time || !id) return;
        console.log("Envoie de l'objectif...");

        createObjective({
            swim: parseInt(selectedSwim, 10),
            swimmerId: parseInt(id as string, 10),
            distance: distance,
            time: time,
        });
        console.log("Objectif envoyé !!");

        router.push(`/objectives/swimmer/${id}`);
    };

    return (
        <div className="add-objective-container">
            <main className="main-container">
                <div className="add-objective-card">
                    <h2 className="title-card">Ajouter un objectif</h2>
                    <form
                        className="form-add-objective"
                        onSubmit={(e) => {
                            e.preventDefault();
                            addObjective();
                        }}
                    >
                        <label className="label-objective">Type de nages</label>
                        <select
                            className="select-swims"
                            value={selectedSwim}
                            onChange={(e) => setSelectedSwim(e.target.value)}
                        >
                            <option value="">-- Sélectionnez une nage --</option>
                            {SwimMapping.map((swim) => (
                                <option key={swim.id} value={swim.id}>
                                    {swim.name}
                                </option>
                            ))}
                        </select>

                        <label className="label-objective">Distance</label>
                        <input
                            type="number"
                            className="input-objectives"
                            placeholder="100"
                            value={distance}
                            onChange={(e) => setDistance(e.target.value)}
                        />

                        <label className="label-objective">Durée</label>
                        <input
                            type="number"
                            className="input-objectives"
                            placeholder="3"
                            value={time}
                            onChange={(e) => setTime(e.target.value)}
                        />

                        <button type="submit">Ajouter</button>
                    </form>
                </div>
            </main>
        </div>
    );
};

export default FormObjective;
