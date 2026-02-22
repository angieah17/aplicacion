import { useEffect, useRef, useState } from 'react';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
const ENDPOINT = '/api/public/preguntas';

let cache = null;

export default function usePublicPreguntas() {
  const [data, setData] = useState(cache);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const abortRef = useRef(null);
  const mountedRef = useRef(true);

  useEffect(() => {
    mountedRef.current = true;
    // fetch on mount if no cache
    if (!cache) {
      fetchData();
    }
    return () => {
      mountedRef.current = false;
      if (abortRef.current) {
        abortRef.current.abort();
      }
    };
  }, []);

  async function fetchData({ force = false } = {}) {
    if (loading) return;
    if (cache && !force) {
      setData(cache);
      return;
    }

    const controller = new AbortController();
    abortRef.current = controller;
    setLoading(true);
    setError(null);

    try {
      const resp = await axios.get(`${BASE_URL}${ENDPOINT}`, {
        signal: controller.signal,
        headers: { 'Accept': 'application/json' },
      });
      if (!mountedRef.current) return;
      cache = resp.data;
      setData(resp.data);
    } catch (err) {
      // ignore cancellations
      if (axios.isAxiosError(err) && err.code === 'ERR_CANCELED') return;
      if (!mountedRef.current) return;
      setError(err);
    } finally {
      if (!mountedRef.current) return;
      setLoading(false);
    }
  }

  function refresh() {
    return fetchData({ force: true });
  }

  return { data, loading, error, refresh };
}
