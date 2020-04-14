package br.com.banco.cliente.service

import br.com.banco.cliente.web.api.ClienteApiDelegate
import br.com.banco.cliente.web.api.model.ClienteRequest
import br.com.banco.cliente.web.api.model.ClienteResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ClienteService : ClienteApiDelegate{
    override fun getCliente(id: Long): ResponseEntity<ClienteResponse> =
            ResponseEntity.ok(gerarCliente(id))

    override fun putCliente(id: Long, clienteRequest: ClienteRequest): ResponseEntity<ClienteResponse>? =
            ResponseEntity.accepted().body(gerarCliente(id))

    override fun deleteCliente(id: Long): ResponseEntity<Void> =
            ResponseEntity.noContent().build()

    override fun getAllclientes(): ResponseEntity<MutableList<ClienteResponse>> {
        val listCliente = ArrayList<ClienteResponse>()
        listCliente.add(gerarCliente(1))
        listCliente.add(gerarCliente(2))
        return ResponseEntity.ok(listCliente.toMutableList())
    }

    override fun postCliente(clienteRequest: ClienteRequest): ResponseEntity<ClienteResponse> =
            ResponseEntity.status(HttpStatus.CREATED)
                    .body(gerarCliente(1))

    fun gerarCliente(numeroCliente:Long): ClienteResponse{
        val cliente = ClienteResponse()
        cliente.apply {
            nome = "Cliente $numeroCliente"
            cnpj = numeroCliente.toInt()
            dataNascimento = "1992-02-21"
            id = numeroCliente.toString()
            profissao = "Analista $numeroCliente"
            email = "$numeroCliente@redspark.io"
        }
        return cliente
    }

}