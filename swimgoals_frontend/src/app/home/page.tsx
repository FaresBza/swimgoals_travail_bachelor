"use client";

import { useEffect, useState } from "react";
import useGroupApi from "../hooks/useGroupApi";

import './../styles/BackgroundImage.scss'
import './../styles/Home.scss'
import './../styles/Scroll.scss'

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
            <div className="container blur">
                <div className="scrollable-container">
                    <main className="list-groups">
                        <h1 className="main-title">Groupes</h1>
                        <ul>
                            {groups.length > 0 ? (
                                groups.map((group, index) => <li key={index}>{group.name}</li>)
                            ) : (
                                <p>Aucun groupe trouvé.</p>
                            )}
                        </ul>
                    </main>
                </div>
            </div>
        </div>
    );
};

export default Home;
