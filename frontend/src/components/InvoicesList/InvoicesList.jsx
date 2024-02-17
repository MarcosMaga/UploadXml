import { useState, useEffect } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChevronRight, faChevronDown } from '@fortawesome/free-solid-svg-icons';
import { format } from 'date-fns';
import './InvoicesList.css';
import DownloadButton from "../DownloadButton/DownloadButton";

function InvoicesList(){
    const [invoices, setInvoices] = useState(null);
    const [page, setPage] = useState(0);

    useEffect(() => {
        getInvoices();
    }, []);

    function getInvoices(){
        fetch(`http://localhost:8080/api/invoice?page=${page}&size=5`, {
                method: 'GET',
            }).then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao enviar os arquivos para o servidor');
                }
                return response.json();
            }).then(data => {
                if(data != null){
                    if(!invoices)
                        setInvoices(data.content);
                    else{
                        let newInvoices = Array.from(invoices);
                        data.content.forEach((invoice) => {
                            newInvoices.push(invoice);
                        })
                        setInvoices(newInvoices);
                    }
                    console.log(data);
                    setPage(page+1);
                }
            }).catch(error => {
                console.error('Erro:', error);
                setPage(-1);
            });
    }

    const [visibleInvoiceInfo, setVisibleInvoiceInfo] = useState({});

    function actionList(idx){
        setVisibleInvoiceInfo(prevState => ({
            ...prevState,
            [idx]: !prevState[idx]
        }));
    }

    return (
        <div className="InvoicesList">
            <h1>NF's cadastradas</h1>
            {!invoices ? ( <p>Nenhum NF encontrado. </p>) : (
                <ul>
                    {invoices.map((invoice, idx) =>
                            <div key={idx} className="InvoiceItem">
                                <li>
                                    <strong><a href="#"  onClick={() => actionList(idx)}>{!visibleInvoiceInfo[idx] ? <FontAwesomeIcon icon={faChevronRight}/> : <FontAwesomeIcon icon={faChevronDown}/>}</a> {invoice.id}</strong>
                                    <DownloadButton binary={invoice.binary.fileXml}/>
                                    <ul id={idx} className={`InvoiceInfo ${visibleInvoiceInfo[idx] ? 'visible' : ''}`}>
                                        <div>
                                            <li><strong>dhEmi: </strong> {format(new Date(invoice.dhEmi), 'dd/MM/yyyy HH:mm:ss')}</li>
                                            <li><strong>nNF: </strong> {invoice.nNF}</li>
                                            <li><strong>cUF: </strong> {invoice.cUF}</li>
                                            <li><strong>emitCnpj: </strong> {invoice.emitCnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5')}</li>
                                            <li><strong>xFant: </strong> {invoice.xFant}</li>
                                            <li><strong>destCnpj: </strong> {invoice.destCnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5')}</li>
                                            <li><strong>xNome: </strong> {invoice.xNome}</li>
                                            <li><strong>vTotTrib: </strong> {invoice.vTotTrib}</li>
                                            <li><strong>vNF: </strong> {invoice.vNF}</li>
                                        </div>
                                    </ul>
                                </li>
                                
                            </div>
                        )}
                </ul>
            )}{page != -1 ? <button onClick={() => {getInvoices()}}>Carregar mais</button> : <p>Nenhum dado para carregar</p>}
            
        </div>
    )
}

export default InvoicesList;