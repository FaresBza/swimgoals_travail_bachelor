"use client";

import { useCallback, useEffect, useRef, useState } from "react";

import AddButton from "../components/AddButton";
import FormGroup from "../components/FormGroup";

import './../styles/BackgroundImage.scss'
import './../styles/Home.scss'
import './../styles/Scroll.scss'
import './../styles/FormGroup.scss';

import useGroupApi from "../hooks/useGroupApi";
import GroupData from "../data/GroupData";

const Home = () => {
    const [openForm, setOpenForm] = useState<boolean>(false);
    const [isVisible, setIsVisible] = useState(false);
    const [groups, setGroups] = useState<GroupData[]>([]);

    const formRef = useRef<HTMLDivElement>(null);
    const { fetchAllGroups } = useGroupApi();

    const loadGroups = useCallback(async () => {
        try {
            const data = await fetchAllGroups();
            setGroups(data);
        } catch (error) {
            console.error("Erreur lors du chargement des groupes :", error);
        }
    }, [fetchAllGroups]);

    useEffect(() => {
        loadGroups();
    }, [loadGroups]);

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
        <div>
            <div className="container blur">
                <div className="scrollable-container">
                    <h1 className="main-title">Groupes</h1>
                    <main className="list-groups">
                        {groups.map((group, index) => (
                            <div key={index} className="group-card">
                                <h2>{group.name}</h2>
                                <p>Coach ID : {group.coachId}</p>
                            </div>
                        ))}
                    </main>

                    {openForm && (
                        <div
                            ref={formRef}
                            className={`form-container ${isVisible ? "fade-in" : "fade-out"}`}
                        >
                            <FormGroup />
                        </div>
                    )}
                </div>
            </div>

            <footer>
                <AddButton
                    onClick={() => {
                        setOpenForm(true);
                        setIsVisible(true);
                    }}
                />
            </footer>
        </div>
    );
};

export default Home;
