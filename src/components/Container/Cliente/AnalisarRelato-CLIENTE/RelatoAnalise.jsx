import React, { useState } from 'react';
import styles from './RelatoAnalise.module.css';

function RelatoAnalise({ relato, onClose }) {
  const [assunto, setAssunto] = useState(relato.assunto || '');
  const [categoria, setCategoria] = useState(relato.categoria || 'INFRAESTRUTURA');
  const [arquivo, setArquivo] = useState(null);
  const status = relato.status || 'PENDENTE';
  const data = relato.data ? relato.data.substring(0, 10) : '';

  const handleArquivoChange = (e) => {
    setArquivo(e.target.files[0]);
  };

  const handleEditar = () => {
  const dadosParaEnvio = {
    assunto,
    categoria,
    tipoRelato: relato.tipoRelato === 'SUGESTAO' ? 'SUGESTAO' : 'DENUNCIA',
    anonimo: relato.anonimo || false,
  };

  fetch(`http://localhost:8080/relatos/${relato.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(dadosParaEnvio),
  })
  .then(async (res) => {
    if (!res.ok) {
      const errorBody = await res.json().catch(() => ({}));
      const errorMsg = errorBody.message || JSON.stringify(errorBody);
      throw new Error(`Erro ao editar relato: ${errorMsg}`);
    }
    alert('Relato atualizado com sucesso!');
    onClose();
  })
  .catch(err => {
    console.error(err);
    alert(`Falha ao editar o relato: ${err.message}`);
  });
};


  const handleExcluir = () => {
    if (!window.confirm('Tem certeza que deseja excluir este relato?')) return;

    fetch(`http://localhost:8080/relatos/${relato.id}`, {
      method: 'DELETE',
    })
      .then(res => {
        if (!res.ok) throw new Error('Erro ao excluir relato');
        alert('Relato excluído com sucesso!');
        onClose();
      })
      .catch(err => {
        console.error(err);
        alert('Falha ao excluir o relato.');
      });
  };

  return (
    <div className={styles.wrapper}>
      <span className={styles.iconClose} onClick={onClose}>&times;</span>

      <h2 className={styles.title}>Análise do Relato</h2>

      <div className={styles.formGroup}>
        <label>Assunto:</label>
        <input
          type="text"
          value={assunto}
          onChange={e => setAssunto(e.target.value)}
        />
      </div>

      <div className={styles.formGroup}>
        <label>Categoria:</label>
        <select
          value={categoria}
          onChange={e => setCategoria(e.target.value)}
        >
          <option value="INFRAESTRUTURA">Infraestrutura</option>
          <option value="ENSINO">Ensino</option>
          <option value="SEGURANCA">Segurança</option>
        </select>
      </div>

      <div className={styles.formGroup}>
        <label>Data:</label>
        <input type="date" value={data} disabled />
      </div>

      <div className={styles.formGroup}>
        <label>Arquivo:</label>
        <input type="file" onChange={handleArquivoChange} />
        {arquivo && <p>Arquivo selecionado: {arquivo.name}</p>}
      </div>

      <div className={styles.formGroup}>
        <label>Status:</label>
        <span className={styles.statusLabel}>{status}</span>
      </div>

      <div className={styles.buttonGroup}>
        <button className={styles.botaoEditar} onClick={handleEditar}>Editar</button>
        <button className={styles.botaoExcluir} onClick={handleExcluir}>Excluir</button>
      </div>
    </div>
  );
}

export default RelatoAnalise;
