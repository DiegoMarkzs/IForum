import React, { useEffect, useState } from 'react';
import styles from './TabelaListarRelatos.module.css';

function Container({ onClose, onRelatoClick }) {
  const [relatos, setRelatos] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/relatos')
      .then(res => {
        if (!res.ok) throw new Error('Erro ao carregar relatos');
        return res.json();
      })
      .then(data => {
        setRelatos(data);
      })
      .catch(err => {
        console.error(err);
        setRelatos([]);
      });
  }, []);

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>
        &times;
      </span>

      <h2 className={styles.title}>Enviados</h2>

      <div className={styles.tableContainer}>
        <table className={styles.tabela}>
          <thead>
            <tr>
              <th>Tipo</th>
              <th>Categoria</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {relatos.length === 0 ? (
              <tr>
                <td colSpan="3">Nenhum relato encontrado</td>
              </tr>
            ) : (
              relatos.map((relato) => (
                <tr
                  key={relato.id}
                  style={{ cursor: 'pointer' }}
                  onClick={() => onRelatoClick(relato)}
                >
                  <td>{relato.tipo || ''}</td>
                  <td>{relato.categoria}</td>
                  <td>{relato.status}</td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}


export default Container;
