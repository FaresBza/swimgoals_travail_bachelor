import { swimEnum } from "@/app/enum/swimEnum";

export const getSwimColor = (swimType: swimEnum) => {
    switch (swimType) {
        case 1:
            return 'rgba(64, 224, 208, 1)'; // Bleu Turquoise
        case 2:
            return 'rgba(0, 128, 0, 1)'; // Vert
        case 3:
            return 'rgba(255, 165, 0, 1)'; // Orange
        case 4:
            return 'rgba(0, 0, 139, 1)'; // Bleu foncé
        case 5:
            return 'rgba(255, 0, 0, 1)'; // Rouge
        case 6:
            return 'rgba(148, 0, 211, 1)'; // Violet
        case 7:
            return 'rgb(211, 0, 144)'; // Rose
        default:
            return 'rgba(75, 192, 192, 1)'; // Par défaut
    }
};