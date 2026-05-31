import axios from "axios";

const API_URL ="http://localhost:8080/api/game";


export const startGame = (symbol) =>
    axios.post(`${API_URL}/start`,{
        playerSymbol: symbol, 

    });

export const playMove = (row,col) =>
    axios.post(`${API_URL}/move`,{
        row,
        col
    });

export const getGame = () =>
    axios.get(`${API_URL}`);

export const restartGame = () =>
    axios.post(`${API_URL}/restart`);