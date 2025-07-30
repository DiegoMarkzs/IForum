import React, { useState } from 'react';
import styles from './TabelaFazerRelato.module.css';

function TabelaFazerRelato({ onClose }) {
  const [tipo, setTipo] = useState('DENUNCIA');
  const [categoria, setCategoria] = useState('INFRAESTRUTURA');
  const [descricao, setDescricao] = useState('');
  const [anonimo, setAnonimo] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    const relato = {
    tipoRelato: tipo,
    categoria: categoria,
    assunto: descricao,
    anonimo: anonimo,
    status: "ABERTO",
    data: new Date().toISOString().slice(0, 10)
    };
    fetch('http://localhost:8080/relatos/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(relato)
    })
      .then(res => {
        if (!res.ok) throw new Error('Erro ao enviar relato');
        alert('Relato enviado com sucesso!');
        onClose();
      })
      .catch(err => {
        console.error(err);
        alert('Falha ao enviar o relato.');
      });
  };

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>&times;</span>
      <h2 className={styles.title}>Fazer Relato</h2>
      <form onSubmit={handleSubmit} className={styles.form}>
        <textarea
          className={styles.textarea}
          placeholder="Descreva o relato..."
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          required
        />

        <div className={styles.selectGroup}>
          <label>Tipo:</label>
          <select value={tipo} onChange={(e) => setTipo(e.target.value)}>
            <option value="DENUNCIA">Denúncia</option>
            <option value="SUGESTAO">Sugestão</option>
          </select>

          <label>Categoria:</label>
          <select value={categoria} onChange={(e) => setCategoria(e.target.value)}>
            <option value="INFRAESTRUTURA">Infraestrutura</option>
            <option value="ENSINO">Ensino</option>
            <option value="SEGURANCA">Segurança</option>
          </select>
        </div>

        <div className={styles.checkboxGroup}>
          <input
            type="checkbox"
            checked={anonimo}
            onChange={(e) => setAnonimo(e.target.checked)}
            id="anonimo"
          />
          <label htmlFor="anonimo">Enviar anonimamente</label>
        </div>

        <button type="submit" className={styles.botaoEnviar}>Enviar</button>
      </form>
    </div>
  );
}

export default TabelaFazerRelato;
