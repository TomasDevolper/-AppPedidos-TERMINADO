CREATE DATABASE BDPedidos
GO

USE BDPedidos
GO

CREATE TABLE TBProducto(
Codigo INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
Nombre VARCHAR(70) NOT NULL,
Precio DECIMAL(8,2),
Stock INT,
CHECK(Stock >= 0)
)
GO
CREATE TABLE TBPedido(
Codigo INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
CodigoProducto INT NOT NULL,
Fecha DATE,
Precio DECIMAL(8,2),
Cantidad INT,
Total DECIMAL(10,2),
FOREIGN KEY(CodigoProducto) REFERENCES TBProducto(Codigo)
)
GO

INSERT INTO TBProducto VALUES('Hoja bond A4 100', 15, 30)
GO


CREATE PROCEDURE sp_agregar
@codigoProducto INT,
@cantidad INT,
@mensaje VARCHAR(15) OUTPUT
AS
	SET NOCOUNT ON;
	DECLARE @precio DECIMAL(8,2)
	DECLARE @total DECIMAL(10,2)
	BEGIN TRAN
		BEGIN TRY
			UPDATE TBProducto SET Stock =Stock-@cantidad WHERE Codigo = @codigoProducto
			
			SET @precio = (SELECT precio FROM TBProducto WHERE Codigo= @codigoProducto)
			SET @total = @precio * @cantidad			
			
			INSERT INTO TBPedido VALUES(@codigoProducto,GETDATE(),@precio,@cantidad,@total)
			
			SET @mensaje = 'AGREGADO'
			COMMIT
		END TRY
		BEGIN CATCH
			SET @mensaje = 'ERROR'
			ROLLBACK
		END CATCH
GO
