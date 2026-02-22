import React from 'react';
import { ActivityIndicator, Button, FlatList, StyleSheet, Text, View } from 'react-native';
import usePublicPreguntas from '../hooks/usePublicPreguntas';

export default function ListaPreguntasScreen() {
  const { data, loading, error, refresh } = usePublicPreguntas();

  const renderItem = ({ item }) => (
    <View style={styles.item}>
      <Text style={styles.title}>{item.enunciado}</Text>
      <Text style={styles.meta}>Temática: {item.tematica}</Text>
      <Text style={styles.meta}>Tipo: {item.tipo}</Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <Text style={styles.note}>Lista de preguntas activas</Text>

      {loading && (
        <View style={styles.center}>
          <ActivityIndicator size="large" />
        </View>
      )}

      {error && (
        <View style={styles.center}>
          <Text style={styles.error}>Error al cargar preguntas.</Text>
          <Button title="Reintentar" onPress={refresh} />
        </View>
      )}

      {!loading && !error && (
        <FlatList
          data={data || []}
          keyExtractor={(item) => String(item.id)}
          renderItem={renderItem}
          contentContainerStyle={styles.list}
          ListEmptyComponent={<Text style={styles.empty}>No hay preguntas disponibles.</Text>}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16, backgroundColor: '#fff' },
  center: { alignItems: 'center', justifyContent: 'center', marginTop: 24 },
  item: { padding: 12, borderBottomWidth: 1, borderBottomColor: '#eee' },
  title: { fontSize: 16, fontWeight: '600', marginBottom: 6 },
  meta: { fontSize: 14, color: '#555' },
  list: { paddingBottom: 24 },
  error: { color: 'red', marginBottom: 8 },
  empty: { textAlign: 'center', marginTop: 24, color: '#666' },
  note: { fontSize: 24, fontWeight: '700', color: '#111', marginBottom: 16 },
});
