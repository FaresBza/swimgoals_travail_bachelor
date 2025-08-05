"use client"

import { useEffect } from "react";
import useLocalStorage from "../hooks/useLocalStorage";

const ChartSwimmer = () => {

    const { swimmerId, roleId, recoverUserId, recoverRoleId } = useLocalStorage();

    useEffect(() => {
        recoverRoleId()
        if(roleId) { recoverUserId() }
    })

    return (
        <>
            <h1>{swimmerId}</h1>
        </>
    )
}

export default ChartSwimmer;