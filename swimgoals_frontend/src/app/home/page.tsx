"use client";

import { useEffect, useState } from "react";
import useGroupApi from "../hooks/useGroupApi";

const Home = () => {
    const [groups, setGroups] = useState<{ name: string }[]>([]);
    const { fetchAllGroups } = useGroupApi();

    const loadGroups = async () => {
        try {
            const data = await fetchAllGroups();
            if (data && Array.isArray(data)) {
                setGroups(data);
            } else {
                setGroups([]);
            }
        } catch (error) {
            console.error("Erreur lors de la récupération des groupes :", error);
            setGroups([]);
        };
    };

    useEffect(() => {
        loadGroups();
    }, []);

    return (
        <div>
            <h1>Bienvenue sur la page des coachs et swimmers</h1>
            <ul>
                {groups.length > 0 ? (
                    groups.map((group, index) => <li key={index}>{group.name}</li>)
                ) : (
                    <p>Aucun groupe trouvé.</p>
                )}
            </ul>
        </div>
    );
};

export default Home;
