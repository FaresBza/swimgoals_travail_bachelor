"use client"

import { useState } from "react";
import { userRoleEnum } from "../enum/userRoleEnum";

const useLocalStorage = () => {

    const [roleId, setRoleId] = useState<number>(0);
    const [coachId, setCoachId] = useState<number>(0);
    const [swimmerId, setSwimmerId] = useState<number>(0);

    const recoverUserId = () => {
        const storedUser = localStorage.getItem("user");

        if (!storedUser) {
            console.error("Aucun utilisateur trouvé dans le localStorage");
            return;
        }

        const user = JSON.parse(storedUser);

        switch (user.roleId) {
            case userRoleEnum.coach:
                setCoachId(user.id)
                break;
            case userRoleEnum.swimmer:
                setSwimmerId(user.id);
                break;
            default:
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

    return {coachId, swimmerId, roleId, recoverUserId, recoverRoleId}

}

export default useLocalStorage;