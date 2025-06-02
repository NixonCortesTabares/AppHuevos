package com.nixoncortes1005.apphuevos2.di

import android.content.Context
import androidx.room.Room
import com.nixoncortes1005.apphuevos2.Balance.Data.Dao.daoBalance
import com.nixoncortes1005.apphuevos2.Balance.Data.RepoImp.BalanceVentasRepoImp
import com.nixoncortes1005.apphuevos2.Balance.Domain.Repository.BalanceRepository
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.Dao.daoInsumos
import com.nixoncortes1005.apphuevos2.Control_insumos.Data.RepoImp.InsumosRepositoryImp
import com.nixoncortes1005.apphuevos2.Control_insumos.Domain.Repository.InsumosRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoCliente
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPagos
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoSemanal
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoPedidoUnitario
import com.nixoncortes1005.apphuevos2.Pedidos.Data.DAO.daoProducto
import com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation.ClienteRepositoryImp
import com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation.PagosRepositoryImp
import com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation.PedidoSemanalRepositoryImp
import com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation.PedidosUnitariosRepositoryImp
import com.nixoncortes1005.apphuevos2.Pedidos.Data.RepoImplementation.ProductoRepositoryImp
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.ClienteRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PagosRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidoSemanalRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.PedidosUnitariosRepository
import com.nixoncortes1005.apphuevos2.Pedidos.Domain.Repository.ProductoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePedidosDataBase(@ApplicationContext context: Context): PedidosDataBase {
        return Room.databaseBuilder(
            context,
            PedidosDataBase::class.java,
            "database_pedidos"
        )   .build()
    }


    @Provides
    fun provideBalanceDao(dataBase: PedidosDataBase): daoBalance{
        return dataBase.daoBalance()
    }

    @Provides
    fun provideInsumosdao(dataBase: PedidosDataBase): daoInsumos{
        return dataBase.daoInsumos()
    }

    @Provides
    fun provideDaoCliente(dataBase: PedidosDataBase): daoCliente{
        return dataBase.daoCliente()
    }

    @Provides
    fun provideDaoPagos(dataBase: PedidosDataBase): daoPagos{
        return dataBase.daoPagos()
    }

    @Provides
    fun provideDaoPedidoSemanal(dataBase: PedidosDataBase): daoPedidoSemanal{
        return dataBase.daoPedidoSemanal()
    }

    @Provides
    fun provideDaoPedidoUnitario(dataBase: PedidosDataBase): daoPedidoUnitario{
        return dataBase.daoPedidoUnitario()
    }

    @Provides
    fun provideDaoProducto(dataBase: PedidosDataBase): daoProducto{
        return dataBase.daoProducto()
    }

    @Provides
    @Singleton
    fun provideBalanceRepository(dao: daoBalance, daoInsumos: daoInsumos): BalanceRepository{
        return BalanceVentasRepoImp(
            dao,
            daoInsumos = daoInsumos
        )
    }

    @Provides
    @Singleton
    fun provideInsumosRepository(dao: daoInsumos): InsumosRepository{
        return InsumosRepositoryImp(dao)
    }
    @Provides
    @Singleton
    fun provideClientesRepository(dao: daoCliente): ClienteRepository{
        return ClienteRepositoryImp(dao)
    }
    @Provides
    @Singleton
    fun providePagosRepository(dao: daoPagos, daoPU: daoPedidoUnitario): PagosRepository{
        return PagosRepositoryImp(dao, daoPU)
    }

    @Provides
    @Singleton
    fun providePedidoSemanalRepository(dao: daoPedidoSemanal): PedidoSemanalRepository{
        return PedidoSemanalRepositoryImp(dao)
    }

    @Provides
    @Singleton
    fun providesPedidoUnitarioRepository(dao: daoPedidoUnitario, daoPagos: daoPagos): PedidosUnitariosRepository{
        return PedidosUnitariosRepositoryImp(dao, daoPagos)
    }

    @Provides
    @Singleton
    fun providesProductoRepository(dao: daoProducto): ProductoRepository{
        return ProductoRepositoryImp(dao)
    }

}