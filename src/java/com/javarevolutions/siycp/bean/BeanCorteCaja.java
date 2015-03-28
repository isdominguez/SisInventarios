/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioGraficaVenta;
import com.javarevolutions.siycp.globals.Global;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

/**
 *
 * @author SergioRios
 */
public class BeanCorteCaja implements Serializable {
    private BubbleChartModel graficaCorteXDia = new BubbleChartModel();
    private boolean showGraficaXDia;
    
    public void init() {
        graficaCorteXDia = new BubbleChartModel();
    }
    
    /**
     * @return the graficaCorteXDia
     */
    public BubbleChartModel getGraficaCorteXDia() {
        return graficaCorteXDia;
    }

    /**
     * @param graficaCorteXDia the graficaCorteXDia to set
     */
    public void setGraficaCorteXDia(BubbleChartModel graficaCorteXDia) {
        this.graficaCorteXDia = graficaCorteXDia;
    }
    
    public void creaGraficaXDiaVentas(List<DominioGraficaVenta> ventas) throws IOException {
        graficaCorteXDia = new BubbleChartModel();
        BubbleChartSeries serie;
        for(DominioGraficaVenta obj: ventas) {
            serie = new BubbleChartSeries();
            serie.setLabel(Global.getPropRB("com.jr.props.menu.corte.grafica.venta")+" # "+obj.getIdVenta());
            serie.setRadius(10);
            serie.setX(obj.getIdVenta().intValue());
            serie.setY(obj.getTotalVenta().intValue());
            graficaCorteXDia.add(serie);
        }
        setShowGraficaXDia(true);
    }

    /**
     * @return the showGraficaXDia
     */
    public boolean isShowGraficaXDia() {
        return showGraficaXDia;
    }

    /**
     * @param showGraficaXDia the showGraficaXDia to set
     */
    public void setShowGraficaXDia(boolean showGraficaXDia) {
        this.showGraficaXDia = showGraficaXDia;
    }
}
