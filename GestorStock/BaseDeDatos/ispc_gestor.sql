--------------------------------------------------------
-- Schema ispc_gestor
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ispc_gestor` ;

-- -----------------------------------------------------
-- Schema ispc_gestor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ispc_gestor`;
USE `ispc_gestor` ;

-- -----------------------------------------------------
-- Table `ispc_gestor`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`turno` (
  `Id_Turno` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`Id_Turno`));

-- -----------------------------------------------------
-- Table `ispc_gestor`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`empresa` (
  `Id_Empresa` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Id_Empresa`));

-- -----------------------------------------------------
-- Table `ispc_gestor`.`nomina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`nomina` (
  `Id_Empleado` INT NOT NULL AUTO_INCREMENT,
  `Apellido` VARCHAR(50) NULL DEFAULT NULL,
  `Nombre` VARCHAR(100) NULL DEFAULT NULL,
  `Tipo_Dni` VARCHAR(5) NULL DEFAULT NULL,
  `Num_Dni` VARCHAR(15) NULL DEFAULT NULL,
  `Rol` VARCHAR(50) NULL DEFAULT NULL,
  `Usuario` VARCHAR(10) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  `Turno` VARCHAR(1) NULL DEFAULT NULL,
  `Id_Turno` INT NULL,
  PRIMARY KEY (`Id_Empleado`),
  CONSTRAINT `Id_Empresa_Nomina`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Turno_Nomina`
    FOREIGN KEY (`Id_Turno`)
    REFERENCES `ispc_gestor`.`turno` (`Id_Turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`nomina` (`Id_Empresa` ASC) VISIBLE;

CREATE INDEX `Id_Turno_idx` ON `ispc_gestor`.`nomina` (`Id_Turno` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`egresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`egresos` (
  `Id_Egreso` INT NOT NULL AUTO_INCREMENT,
  `TipoDePago` VARCHAR(30) NULL DEFAULT NULL,
  `FechaIngresos` DATETIME NULL DEFAULT NULL,
  `Id_Turno` INT NOT NULL,
  `MontoEg` VARCHAR(50) NULL DEFAULT NULL,
  `DescripcionMontoEg` VARCHAR(1000) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  `Id_Empleado` INT NOT NULL,
  PRIMARY KEY (`Id_Egreso`),
  CONSTRAINT `Id_Turno_Egresos`
    FOREIGN KEY (`Id_Turno`)
    REFERENCES `ispc_gestor`.`turno` (`Id_Turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Empresa_Egresos`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Empleado_Egresos`
    FOREIGN KEY (`Id_Empleado`)
    REFERENCES `ispc_gestor`.`nomina` (`Id_Empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Turno_idx` ON `ispc_gestor`.`egresos` (`Id_Turno` ASC) VISIBLE;

CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`egresos` (`Id_Empresa` ASC) VISIBLE;

CREATE INDEX `Id_Empleado_idx` ON `ispc_gestor`.`egresos` (`Id_Empleado` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`fondos_cajas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`fondos_cajas` (
  `Id_Caja` INT NOT NULL AUTO_INCREMENT,
  `FondoInicial` VARCHAR(20) NULL DEFAULT NULL,
  `Id_Turno` INT NOT NULL,
  `Fecha` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`Id_Caja`),
  CONSTRAINT `Id_Turno_fondos_cajas`
    FOREIGN KEY (`Id_Turno`)
    REFERENCES `ispc_gestor`.`turno` (`Id_Turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE INDEX `Id_Turno_idx` ON `ispc_gestor`.`fondos_cajas` (`Id_Turno` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`ingresos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`ingresos` (
  `Id_Ingreso` INT NOT NULL AUTO_INCREMENT,
  `TipoDePago` VARCHAR(30) NULL DEFAULT NULL,
  `FechaIngresos` DATETIME NULL DEFAULT NULL,
  `Id_Turno` INT NOT NULL,
  `MontoIng` VARCHAR(50) NULL DEFAULT NULL,
  `DescripcionMontoIng` VARCHAR(1000) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  `Id_Empleado` INT NOT NULL,
  PRIMARY KEY (`Id_Ingreso`),
  CONSTRAINT `Id_Turno_ingresos`
    FOREIGN KEY (`Id_Turno`)
    REFERENCES `ispc_gestor`.`turno` (`Id_Turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Empresa_ingresos`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Empleado_ingresos`
    FOREIGN KEY (`Id_Empleado`)
    REFERENCES `ispc_gestor`.`nomina` (`Id_Empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE INDEX `Id_Turno_idx` ON `ispc_gestor`.`ingresos` (`Id_Turno` ASC) VISIBLE;

CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`ingresos` (`Id_Empresa` ASC) VISIBLE;

CREATE INDEX `Id_Empleado_idx` ON `ispc_gestor`.`ingresos` (`Id_Empleado` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`Categoria` (
  `Id_Categoria` INT NOT NULL,
  `Categoria` VARCHAR(150) NULL,
  PRIMARY KEY (`Id_Categoria`));
  
-- -----------------------------------------------------
-- Table `ispc_gestor`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`rol` (
  `Id_Rol` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  PRIMARY KEY (`Id_Rol`),
  CONSTRAINT `Id_Empresa_Rol`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`rol` (`Id_Empresa` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`unidades_medida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`unidades_medida` (
  `Id_UnidadMedida` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  PRIMARY KEY (`Id_UnidadMedida`),
  CONSTRAINT `Id_Empresa_unidades_medida`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`unidades_medida` (`Id_Empresa` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`stock` (
  `Id_Stock` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `Cantidad` INT NULL DEFAULT NULL,
  `Precio_u` VARCHAR(10) NULL DEFAULT NULL,
  `Id_UnidadMedida` INT NOT NULL,
  `Codigo_Barras` VARCHAR(20) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  PRIMARY KEY (`Id_Stock`),
  CONSTRAINT `Id_UnidadMedida_stock`
    FOREIGN KEY (`Id_UnidadMedida`)
    REFERENCES `ispc_gestor`.`unidades_medida` (`Id_UnidadMedida`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Empresa_stock`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_UnidadMedida_idx` ON `ispc_gestor`.`stock` (`Id_UnidadMedida` ASC) VISIBLE;

CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`stock` (`Id_Empresa` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `ispc_gestor`.`tipo_pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`tipo_pago` (
  `Id_TipoPago` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`Id_TipoPago`));

-- -----------------------------------------------------
-- Table `ispc_gestor`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`Cliente` (
  `Id_Cliente` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Apellido` VARCHAR(100) NULL,
  `Domicilio` VARCHAR(300) NULL,
  `Dirección de Envio` VARCHAR(300) NULL,
  `Email` VARCHAR(100) NULL,
  `Telefono` VARCHAR(10) NULL,
  `Id_Pedido` INT NOT NULL,
  PRIMARY KEY (`Id_Cliente`));

-- -----------------------------------------------------
-- Table `ispc_gestor`.`ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`ventas` (
  `Id_Venta` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATETIME NULL DEFAULT NULL,
  `Descrpcion` VARCHAR(100) NULL DEFAULT NULL,
  `Monto` VARCHAR(20) NULL DEFAULT NULL,
  `Id_Empresa` INT NOT NULL,
  `Id_Empleado` INT NOT NULL,
  `Id_Turno` INT NOT NULL,
  `Id_Proveedor` INT NULL,
  `Id_Producto` INT NOT NULL,
  PRIMARY KEY (`Id_Venta`),
  CONSTRAINT `Id_Empresa_ventas`
    FOREIGN KEY (`Id_Empresa`)
    REFERENCES `ispc_gestor`.`empresa` (`Id_Empresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Empleado_ventas`
    FOREIGN KEY (`Id_Empleado`)
    REFERENCES `ispc_gestor`.`nomina` (`Id_Empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Turno_ventas`
    FOREIGN KEY (`Id_Turno`)
    REFERENCES `ispc_gestor`.`turno` (`Id_Turno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Empresa_idx` ON `ispc_gestor`.`ventas` (`Id_Empresa` ASC) VISIBLE;

CREATE INDEX `Id_Empleado_idx` ON `ispc_gestor`.`ventas` (`Id_Empleado` ASC) VISIBLE;

CREATE INDEX `Id_Turno_idx` ON `ispc_gestor`.`ventas` (`Id_Turno` ASC) VISIBLE;

CREATE INDEX `Id_Poveedor_idx` ON `ispc_gestor`.`ventas` (`Id_Proveedor` ASC) VISIBLE;

CREATE INDEX `Id_Producto_idx` ON `ispc_gestor`.`ventas` (`Id_Producto` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `ispc_gestor`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`Producto` (
  `Id_producto` INT NOT NULL,
  `Id_Categoria` INT NOT NULL,
  `Id_Proveedor` INT NOT NULL,
  `Nombre` VARCHAR(100) NULL,
  `Precio` VARCHAR(10) NULL,
  `Peso` VARCHAR(10) NULL,
  `Imagen` VARCHAR(100) NULL,
  `Cantidad` VARCHAR(10) NULL,
  `Observaciones` VARCHAR(1000) NULL,
  PRIMARY KEY (`Id_producto`),
  CONSTRAINT `Id_Categoria_Producto`
    FOREIGN KEY (`Id_Categoria`)
    REFERENCES `ispc_gestor`.`Categoria` (`Id_Categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Categoria_idx` ON `ispc_gestor`.`Producto` (`Id_Categoria` ASC) VISIBLE;

CREATE INDEX `Id_Proveedor_idx` ON `ispc_gestor`.`Producto` (`Id_Proveedor` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `ispc_gestor`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`proveedor` (
  `Id_Proveedor` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(500) NULL DEFAULT NULL,
  `Id_Producto` INT NOT NULL,
  PRIMARY KEY (`Id_Proveedor`),
  CONSTRAINT `Id_Producto_proveedor`
    FOREIGN KEY (`Id_Producto`)
    REFERENCES `ispc_gestor`.`Producto` (`Id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE INDEX `Id_Producto_idx` ON `ispc_gestor`.`proveedor` (`Id_Producto` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `ispc_gestor`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`Pedido` (
  `Id_Pedido` INT NOT NULL,
  `Id_Cliente` INT NOT NULL,
  `Id_Comprobante` INT NOT NULL,
  `Id_Categoria` INT NOT NULL,
  `Id_Producto` INT NOT NULL,
  PRIMARY KEY (`Id_Pedido`),
  CONSTRAINT `Id_Cliente_Pedido`
    FOREIGN KEY (`Id_Cliente`)
    REFERENCES `ispc_gestor`.`Cliente` (`Id_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Categoria_Pedido`
    FOREIGN KEY (`Id_Categoria`)
    REFERENCES `ispc_gestor`.`Categoria` (`Id_Categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Producto_Pedido`
    FOREIGN KEY (`Id_Producto`)
    REFERENCES `ispc_gestor`.`Producto` (`Id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Cliente_idx` ON `ispc_gestor`.`Pedido` (`Id_Cliente` ASC) VISIBLE;

CREATE INDEX `Id_Comprobante_idx` ON `ispc_gestor`.`Pedido` (`Id_Comprobante` ASC) VISIBLE;

CREATE INDEX `Id_Categoria_idx` ON `ispc_gestor`.`Pedido` (`Id_Categoria` ASC) VISIBLE;

CREATE INDEX `Id_Producto_idx` ON `ispc_gestor`.`Pedido` (`Id_Producto` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `ispc_gestor`.`Comprobante_Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`Comprobante_Pago` (
  `Id_Comprobante` INT NOT NULL,
  `Fecha` DATE NULL,
  `Razon_Social` INT NULL,
  `Monto` VARCHAR(10) NULL,
  `Id_Pedido` INT NOT NULL,
  `Id_TipoPago` INT NOT NULL,
  PRIMARY KEY (`Id_Comprobante`),
  CONSTRAINT `Id_Pedido_Comprobante_Pago`
    FOREIGN KEY (`Id_Pedido`)
    REFERENCES `ispc_gestor`.`Pedido` (`Id_Pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_TipoPago_Comprobante_Pago`
    FOREIGN KEY (`Id_TipoPago`)
    REFERENCES `ispc_gestor`.`tipo_pago` (`Id_TipoPago`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Pedido_idx` ON `ispc_gestor`.`Comprobante_Pago` (`Id_Pedido` ASC) VISIBLE;

CREATE INDEX `Id_TipoPago_idx` ON `ispc_gestor`.`Comprobante_Pago` (`Id_TipoPago` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `ispc_gestor`.`Delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ispc_gestor`.`Delivery` (
  `Id_Delivery` INT NOT NULL,
  `Id_Pedido` INT NOT NULL,
  `Id_Cliente` INT NOT NULL,
  `Id_Comprobante` INT NOT NULL,
  PRIMARY KEY (`Id_Delivery`),
  CONSTRAINT `Id_Pedido_Delivery`
    FOREIGN KEY (`Id_Pedido`)
    REFERENCES `ispc_gestor`.`Pedido` (`Id_Pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Cliente_Delivery`
    FOREIGN KEY (`Id_Cliente`)
    REFERENCES `ispc_gestor`.`Cliente` (`Id_Cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Id_Comprobante_Delivery`
    FOREIGN KEY (`Id_Comprobante`)
    REFERENCES `ispc_gestor`.`Comprobante_Pago` (`Id_Comprobante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX `Id_Pedido_idx` ON `ispc_gestor`.`Delivery` (`Id_Pedido` ASC) VISIBLE;

CREATE INDEX `Id_Cliente_idx` ON `ispc_gestor`.`Delivery` (`Id_Cliente` ASC) VISIBLE;

CREATE INDEX `Id_Comprobante_idx` ON `ispc_gestor`.`Delivery` (`Id_Comprobante` ASC) VISIBLE;

-- -----------------------------------------------------
-- Foreign keys `ventas`
-- -----------------------------------------------------

ALTER TABLE ventas ADD CONSTRAINT Id_Proveedor_ventas
FOREIGN KEY (Id_Proveedor) REFERENCES proveedor(Id_Proveedor); 

ALTER TABLE ventas ADD CONSTRAINT Id_Producto_ventas
FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_producto); 
 
-- -----------------------------------------------------
-- Foreign keys `Producto`
-- -----------------------------------------------------
 
 ALTER TABLE Producto ADD CONSTRAINT Id_Proveedor_Producto
FOREIGN KEY (Id_Proveedor) REFERENCES proveedor(Id_Proveedor); 
 
-- -----------------------------------------------------
-- Foreign keys `Producto`
-- -----------------------------------------------------
 
  ALTER TABLE Pedido ADD CONSTRAINT Id_Proveedor_Pedido
FOREIGN KEY (Id_Comprobante) REFERENCES Comprobante_Pago(Id_Comprobante); 
