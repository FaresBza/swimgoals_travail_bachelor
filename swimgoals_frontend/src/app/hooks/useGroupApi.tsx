

const useGroupApi = () => {

    const fetchAllGroups = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/groups", {
                method: "GET",
                headers: {
                    Accept: "application/json",
                },
            });

            if (!response.ok) {
                throw new Error(`Erreur HTTP: ${response.status}`);
            }

            return await response.json();
        } catch (e) {
            console.error(e);
            return null;
        }
    }

    return { fetchAllGroups }
}

export default useGroupApi;