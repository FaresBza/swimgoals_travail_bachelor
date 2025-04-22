"use client"

import { useState } from "react";

const useLocalStorage = () => {

    const [roleId, setRoleId] = useState<number>(0);
    const [coachId, setCoachId] = useState<number>(0);

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

    const recoverRoleId = () => {
        const storedUser = localStorage.getItem("user");
        if (!storedUser) {
            console.error("Aucun utilisateur trouvé dans le localStorage");
            return;
        }
        const user = JSON.parse(storedUser);
        setRoleId(user.roleId);
    };

    return {coachId, roleId, recoverCoachId, recoverRoleId}

}

export default useLocalStorage;