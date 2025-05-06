export interface UserGroupData {
    id: number;
    firstname: string;
    lastname: string;
    role: {
        id: number;
        name: string;
    };
}