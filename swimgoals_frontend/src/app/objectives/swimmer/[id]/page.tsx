"use client";

import { useParams } from "next/navigation";

const Objectives = () => {
    const { id } = useParams();

    return (
        <div>
            <h1>Bienvenue sur la page des objectifs du nageur {id}</h1>
        </div>
    );
};

export default Objectives;
