package com.lwglobatechsa.gestosdegastosfinal.ui.reportegastos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.lwglobatechsa.gestosdegastosfinal.ClasesDAO;
import com.lwglobatechsa.gestosdegastosfinal.databinding.FragmentReporteGastoBinding;
import com.lwglobatechsa.gestosdegastosfinal.ui.clases.Gasto;

public class ReporteGasto extends Fragment {
    private PieChart pieChart;
    private BarChart barChart;
    private ClasesDAO clasesDAO;
    private FragmentReporteGastoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReporteGastoViewModel homeViewModel =
                new ViewModelProvider(this).get(ReporteGastoViewModel.class);

        binding = FragmentReporteGastoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pieChart = binding.pieChart;
        barChart = binding.barChart;

        clasesDAO = new ClasesDAO();

        setupCharts();
        loadDataFromFirestore();

        return root;
    }
    private void setupCharts() {
        // Configuración del PieChart
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);

        // Configuración del BarChart
        barChart.getDescription().setEnabled(false);
        barChart.setDrawValueAboveBar(true);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(45);
    }

    private void loadDataFromFirestore() {
        clasesDAO.obtenerGastos(new ClasesDAO.FirestoreCallback<List<Gasto>>() {
            @Override
            public void onComplete(List<Gasto> gastos) {
                Map<String, Integer> gastoPorEmpresa = new HashMap<>();
                Map<String, Double> montoPorEmpresa = new HashMap<>();

                for (Gasto gasto : gastos) {
                    String nombreEmpresa = gasto.getNombreEmpresa();

                    // Actualizar gastoPorEmpresa
                    if (gastoPorEmpresa.containsKey(nombreEmpresa)) {
                        gastoPorEmpresa.put(nombreEmpresa, gastoPorEmpresa.get(nombreEmpresa) + 1);
                    } else {
                        gastoPorEmpresa.put(nombreEmpresa, 1);
                    }

                    // Actualizar montoPorEmpresa
                    if (montoPorEmpresa.containsKey(nombreEmpresa)) {
                        montoPorEmpresa.put(nombreEmpresa, montoPorEmpresa.get(nombreEmpresa) + gasto.getMonto());
                    } else {
                        montoPorEmpresa.put(nombreEmpresa, gasto.getMonto());
                    }
                }

                updatePieChart(gastoPorEmpresa);
                updateBarChart(montoPorEmpresa);
            }
        });
    }

    private void updatePieChart(Map<String, Integer> gastoPorEmpresa) {
        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : gastoPorEmpresa.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Gastos por Empresa");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void updateBarChart(Map<String, Double> montoPorEmpresa) {
        List<BarEntry> entries = new ArrayList<>();
        final String[] labels = new String[montoPorEmpresa.size()];
        int index = 0;

        for (Map.Entry<String, Double> entry : montoPorEmpresa.entrySet()) {
            entries.add(new BarEntry(index, entry.getValue().floatValue()));
            labels[index] = entry.getKey();
            index++;
        }

        BarDataSet dataSet = new BarDataSet(entries, "Monto Total por Empresa");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.invalidate();
    }
}