"use client";

import { useEffect, useRef, useState } from "react";
import useGroupApi from "../hooks/useGroupApi";

import GroupCard from "../components/GroupCard";

import './../styles/BackgroundImage.scss'
import './../styles/Home.scss'
import './../styles/Scroll.scss'
import AddButton from "../components/AddButton";
import FormGroup from "../components/FormGroup";

const Home = () => {
    const [groups, setGroups] = useState<{ name: string }[]>([]);
    const [openForm, setOpenForm] = useState<boolean>(false);
    const [isVisible, setIsVisible] = useState(false);


    const formRef = useRef<HTMLDivElement>(null);
    
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
                        <h1 className="main-title">Groupes</h1>
                    <main className="list-groups">
                        {groups.map((group, index) => {
                            return (
                                <GroupCard
                                    key={index}
                                    name={group.name}
                                />
                            )
                        })}
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
